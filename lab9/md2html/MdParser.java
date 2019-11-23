package md2html;

import java.io.*;
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

    public void parseLine(String line) throws IOException {
        // Возврат к корневой ноде на пустой строке
        if (line.isBlank()) {
            currentNode = rootNode;
            return;
        }

        lineCharIndex = 0;

        if (currentNode.type == MdNodeType.ROOT) {
            // При нахождении в рутовой ноде добавляем либо заголовок, либо параграф
            if (!checkHeading(line)) {
                currentNode = currentNode.addNode(new MdParagraphNode());
            }
        }

        sb = new StringBuilder();

        for (; lineCharIndex < line.length(); lineCharIndex++) {
            parseNextChar(line);
        }

        // Обрабатываем окончание строки
        if (sb.length() > 0) {
            if (currentNode.type == MdNodeType.LINK) {
                MdLinkNode linkNode = (MdLinkNode) currentNode;

                if (linkNode.stage == 0) {
                    MdNode newNode = currentNode.addNode(new MdTextNode(sb.toString()));
                    newNode.isLineEnd = true;
                }

                if (linkNode.stage == 1) {
                    linkNode.url.append(sb.toString() + '\n');
                }
            } else {
                MdNode newNode = currentNode.addNode(new MdTextNode(sb.toString()));
                newNode.isLineEnd = true;
            }
        }
    }

    private void parseNextChar(String line) {
        char c = line.charAt(lineCharIndex);

        if (!isControlChar(c)) {
            // Начинаем обработку ссылки
            if (c == '[') {
                sb = saveAndClearText(sb);
                currentNode = currentNode.addNode(new MdLinkNode());
                return;
            }

            // Переходим на второй этап обработки ссылки
            if (c == ']' && currentNode.type == MdNodeType.LINK) {
                sb = saveAndClearText(sb);
                ((MdLinkNode) currentNode).stage++;
                lineCharIndex += 1;
            } else if (currentNode.type == MdNodeType.LINK) {
                // Завершаем обработку ссылки
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

        sb = saveAndClearText(sb);

        if (currentNode.type == MdNodeType.STYLE && ((MdStyleNode)currentNode).styleType == styleNodeType) {

            // Обрабатываем выход из styleNode
            if (lineCharIndex == line.length() - 1) {
                currentNode.isLineEnd = true;
            }

            currentNode = currentNode.parent;
        } else {

            // Обрабатываем вход в styleNode
            currentNode = currentNode.addNode(new MdStyleNode(styleNodeType));
        }
    }

    private boolean checkHeading(String line) {
        int level = getHeadingLevel(line);

        // Если уровень заголовка равен 0, значит заголовка нет
        if (level > 0) {
            currentNode = currentNode.addNode(new MdHeadingNode(level));
            lineCharIndex = level + 1;
            return true;
        }

        return false;
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

    private StringBuilder saveAndClearText(StringBuilder sb) {
        if (sb.length() == 0) {   
            return sb;           
        }

        currentNode.addNode(new MdTextNode(sb.toString()));
        return new StringBuilder();
    }
}