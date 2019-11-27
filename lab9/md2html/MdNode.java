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

    public String getHtml() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < children.size(); i++) {
            MdNode child = children.get(i);
            boolean isLast = i == children.size() - 1;

            child.writeHtml(sb, isLast);
        }

        return sb.toString();
    }

    protected abstract void writeHtml(StringBuilder sb, boolean isLast);
}