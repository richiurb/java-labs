package md2html;

import java.util.*;

public class Md2HtmlWriter {
    private Map<MdStyleNodeType, String> styleNodesMapping = Map.of(
        MdStyleNodeType.EMPHASIZED, "em",
        MdStyleNodeType.STRONG, "strong",
        MdStyleNodeType.STRIKE, "s",
        MdStyleNodeType.CODE, "code"
    );

    public String getHtml(MdNode node) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < node.children.size(); i++) {
            MdNode child = node.children.get(i);
            boolean isLast = i == node.children.size() - 1;

            if (child.type == MdNodeType.HEADING) {
                int level = ((MdHeadingNode) child).level;
                String elem = "h" + level;
                appendElement(sb, elem, child);
                sb.append('\n');
            } else if (child.type == MdNodeType.PARAGRAPH) {
                String elem = "p";
                appendElement(sb, elem, child);
                sb.append('\n');
            } else if (child.type == MdNodeType.STYLE) {
                MdStyleNode styleNode = (MdStyleNode) child;
                String elem = styleNodesMapping.get(styleNode.styleType);
                appendElement(sb, elem, child);

                if (styleNode.isLineEnd && (!isLast || styleNode.isInLink())) {
                    sb.append('\n');
                }
            } else if (child.type == MdNodeType.TEXT) {
                MdTextNode textNode = (MdTextNode) child;
                sb.append(textNode.text);

                if (textNode.isLineEnd && !isLast) {
                    sb.append('\n');
                }
            } else if (child.type == MdNodeType.LINK) {
                MdLinkNode linkNode = (MdLinkNode) child;
                String elem = "a";
                String attribute = " href='" + linkNode.url + "'";
                sb.append(getOpeningTag(elem + attribute));
                sb.append(getHtml(child));
                sb.append(getClosingTag(elem));

                if (linkNode.isLineEnd && !isLast) {
                    sb.append('\n');
                }
            }
        }

        return sb.toString();
    }

    private String getOpeningTag(String elem) {
        return "<" + elem + ">";
    }

    private String getClosingTag(String elem) {
        return "</" + elem + ">";
    }

    private void appendElement(StringBuilder sb, String elem, MdNode child) {
        sb.append(getOpeningTag(elem));
        sb.append(getHtml(child));
        sb.append(getClosingTag(elem));
    }
}