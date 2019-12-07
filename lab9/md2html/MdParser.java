package md2html;

import java.util.*;

public class MdParser {
    private final int MAX_HEADING_LEVEL = 6;

    private MdRootNode rootNode;
    private MdNode currentNode;
    private String line;
    private int lineCharIndex;
    private StringBuilder sb;

    private static String controlCharacters = "*_-`\\";
    private static Map<String, MdStyleNodeType> styleNodesMapping = Map.of(
        "*", MdStyleNodeType.EMPHASIZED,
        "_", MdStyleNodeType.EMPHASIZED,
        "**", MdStyleNodeType.STRONG,
        "__", MdStyleNodeType.STRONG,
        "--", MdStyleNodeType.STRIKE,
        "`", MdStyleNodeType.CODE
    );

    private static Map<Character, String> htmlSymbolsMapping = Map.of(
        '\"', "&quot;",
        '<', "&lt;",
        '>', "&gt;",
        '&', "&amp;"
    );

    public MdParser() {
        this.rootNode = new MdRootNode();
        this.currentNode = this.rootNode;
    }

    public MdRootNode getRoot() {
        return rootNode;
    }

    public void parseLine(String line) {
        // Returning to the root node on blank line
        if (line.isBlank()) {
            currentNode = rootNode;
            return;
        }

        this.line = line;
        this.lineCharIndex = 0;
        this.sb = new StringBuilder();

        if (currentNode.type == MdNodeType.ROOT) {
            // If in the root node then add heading or paragraph
            lineCharIndex = checkHeading(line);

            if (lineCharIndex == 0) {
                currentNode = currentNode.addNode(new MdParagraphNode());
            }
        }

        for (; lineCharIndex < line.length(); lineCharIndex++) {
            processNextChar();
        }

        if (sb.length() > 0) {
            processLineEnding();
        }
    }

    private void processNextChar() {
        char c = line.charAt(lineCharIndex);

        if (!isControlChar(c)) {
            processNonControlCharacter(c);
        } else if (isInLinkFirstStage()) {
            sb.append(c);
        } else if (c == '\\') {
            processEscapeCharacter(c);
        } else {
            processStyleCharacter(c);
        }
    }

    private void processLineEnding() {
        if (isInLinkFirstStage()) {
            ((MdLinkNode) currentNode).url.append(sb.toString() + '\n');
        } else {
            MdNode newNode = currentNode.addNode(new MdTextNode(sb.toString()));
            newNode.isLineEnd = true;
        }
    }

    private void processNonControlCharacter(char c) {
        if (c == '[') {
            saveAndClearStringBuilder();
            currentNode = currentNode.addNode(new MdLinkNode());
        } else if (currentNode.type == MdNodeType.LINK) {
            switch (c) {
                case ']':
                    saveAndClearStringBuilder();
                    ((MdLinkNode) currentNode).stage++;
                    lineCharIndex += 1;
                    break;
                case ')':
                    ((MdLinkNode) currentNode).url.append(sb.toString());
                    sb = new StringBuilder();
                    currentNode.isLineEnd = line.length() - 1 == lineCharIndex;
                    currentNode = currentNode.parent; 
                    break;
                default:
                    sb.append(c);
            }
        } else {
            String htmlChar = htmlSymbolsMapping.get(c);

            if (htmlChar != null) {
                sb.append(htmlChar);
            } else {
                sb.append(c);
            }
        }
    }

    private void processEscapeCharacter(char c) {
        int nextCharIndex = lineCharIndex + 1;

        if (nextCharIndex != line.length()) {
            char nextChar = line.charAt(nextCharIndex);

            if (isControlChar(nextChar)) {
                sb.append(nextChar);
                lineCharIndex++;
            }
        } else {
            sb.append(c);
        }
    }

    private void processStyleCharacter(char c) {
        String ctrlString = Character.toString(c);
        int nextCharIndex = lineCharIndex + 1;
        char nextChar = nextCharIndex == line.length() ? 0 : line.charAt(nextCharIndex);

        if (currentNode.type != MdNodeType.STYLE && (nextChar == 0 || nextChar == ' ')) {
            sb.append(c);
            return;
        }

        if (nextChar == c) {
            lineCharIndex++;
            ctrlString += c;
        }
        
        MdStyleNodeType styleNodeType = styleNodesMapping.get(ctrlString);

        if (styleNodeType == null) {
            sb.append(ctrlString);
            return;
        }

        saveAndClearStringBuilder();

        if (currentNode.type == MdNodeType.STYLE && ((MdStyleNode) currentNode).styleType == styleNodeType) {
            // Processing end of the style node
            if (lineCharIndex == line.length() - 1) {
                currentNode.isLineEnd = true;
            }

            currentNode = currentNode.parent;
        } else {
            // Processing start of the style node
            currentNode = currentNode.addNode(new MdStyleNode(styleNodeType));
        }
    }
    
    private int checkHeading(String line) {
        int level = getHeadingLevel(line);

        // If the heading level equals to 0 then heading does not exist
        if (level > 0) {
            currentNode = currentNode.addNode(new MdHeadingNode(level));
            return level + 1;
        }

        return 0;
    }

    private int getHeadingLevel(String line) {
        int endOfHeadingIndex = 0;

        for (int i = 0; i <= MAX_HEADING_LEVEL; i++) {
            if (line.charAt(i) != '#') {
                endOfHeadingIndex = i;
                break;
            }
        }

        return endOfHeadingIndex == 0 || line.charAt(endOfHeadingIndex) != ' ' ? 0 : endOfHeadingIndex; 
    }

    private static boolean isControlChar(char c) {
        return controlCharacters.indexOf(c) >= 0;
    }

    private void saveAndClearStringBuilder() {
        if (sb.length() == 0) {   
            return;           
        }

        currentNode.addNode(new MdTextNode(sb.toString()));
        sb.setLength(0);
    }

    private boolean isInLinkFirstStage() {
        return currentNode.type == MdNodeType.LINK && ((MdLinkNode) currentNode).stage == 1;
    }
}