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
}