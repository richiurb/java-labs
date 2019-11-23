package md2html;

import java.util.*;

public class MdParser {
    private final int MAX_HEADING_LEVEL = 6;

    private MdNode rootNode;
    private MdNode currentNode;
    private int lineCharIndex;
    private StringBuilder sb;

    private char[] controlCharacters = new char[] { '*', '_', '-', '`', '\\' };
    private Map<String, MdStyleNodeType> styleNodesMapping = Map.of(
        "*", MdStyleNodeType.EMPHASIZED,
        "_", MdStyleNodeType.EMPHASIZED,
        "**", MdStyleNodeType.STRONG,
        "__", MdStyleNodeType.STRONG,
        "--", MdStyleNodeType.STRIKE,
        "`", MdStyleNodeType.CODE
    );

    private Map<Character, String> htmlSymbolsMapping = Map.of(
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
        return (MdRootNode) rootNode;
    }

    public void parseLine(String line) {
        // Returning to the root node on blank line
        if (line.isBlank()) {
            currentNode = rootNode;
            return;
        }

        lineCharIndex = 0;
        sb = new StringBuilder();

        if (currentNode.type == MdNodeType.ROOT) {
            // If in the root node then add heading or paragraph
            lineCharIndex = checkHeading(line);

            if (lineCharIndex == 0) {
                currentNode = currentNode.addNode(new MdParagraphNode());
            }
        }

        for (; lineCharIndex < line.length(); lineCharIndex++) {
            processNextChar(line);
        }

        if (sb.length() > 0) {
            processLineEnding();
        }
    }

    private void processNextChar(String line) {
        char c = line.charAt(lineCharIndex);

        if (!isControlChar(c)) {
            // Starting link processing
            if (c == '[') {
                saveAndClearStringBuilder();
                currentNode = currentNode.addNode(new MdLinkNode());
                return;
            }

            // Go to the second stage of the link processing
            if (c == ']' && currentNode.type == MdNodeType.LINK) {
                saveAndClearStringBuilder();
                ((MdLinkNode) currentNode).stage++;
                lineCharIndex += 1;
            } else if (currentNode.type == MdNodeType.LINK) {
                // End the link processing
                if (c == ')') {
                    ((MdLinkNode) currentNode).url.append(sb.toString());
                    sb = new StringBuilder();
                    currentNode.isLineEnd = line.length() - 1 == lineCharIndex;
                    currentNode = currentNode.parent;
                } else {
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

            return;
        }

        if (currentNode.type == MdNodeType.LINK && ((MdLinkNode) currentNode).stage == 1) {
            sb.append(c);
            return;
        }

        if (c == '\\') {
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

            return;
        }

        String ctrlString = Character.toString(c);
        int nextCharIndex = lineCharIndex + 1;
        char nextChar = nextCharIndex == line.length() ? 0 : line.charAt(nextCharIndex);

        if (!(currentNode.type == MdNodeType.STYLE) && (nextChar == 0 || nextChar == ' ')) {
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

        if (currentNode.type == MdNodeType.STYLE && ((MdStyleNode)currentNode).styleType == styleNodeType) {
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

    private void processLineEnding() {
        if (currentNode.type == MdNodeType.LINK && ((MdLinkNode) currentNode).stage == 1) {
            ((MdLinkNode) currentNode).url.append(sb.toString() + '\n');
        } else {
            MdNode newNode = currentNode.addNode(new MdTextNode(sb.toString()));
            newNode.isLineEnd = true;
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

        if (endOfHeadingIndex == 0) {
            return 0;
        }

        if (line.charAt(endOfHeadingIndex) == ' ') {
            return endOfHeadingIndex;
        }

        return 0;
    }

    private boolean isControlChar(char character) {
        for (char c : controlCharacters) {
            if (character == c) {
                return true;
            }
        }

        return false;
    }

    private void saveAndClearStringBuilder() {
        if (sb.length() == 0) {   
            return;           
        }

        currentNode.addNode(new MdTextNode(sb.toString()));
        sb = new StringBuilder();
    }
}