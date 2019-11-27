package md2html;

import java.util.*;

public abstract class MdNode {
    public MdNode parent;
    public List<MdNode> children;
    public MdNodeType type;
    public boolean isLineEnd;

    public MdNode() {
        this.children = new ArrayList<MdNode>();
    }

    public MdNode addNode(MdNode node) {
        node.parent = this;
        children.add(node);
        return node;
    }

    /*public String getHtml() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < children.size(); i++) {
            MdNode child = children.get(i);
            boolean isLast = i == children.size() - 1;

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

                if (styleNode.isLineEnd && (isInLink || !isLast)) {
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
                isInLink = true;
                sb.append(child.getHtml());
                isInLink = false;
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
        sb.append(child.getHtml());
        sb.append(getClosingTag(elem));
    }*/
}