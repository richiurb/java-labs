package markup;

import java.util.List;

public abstract class MultiNode implements Node {
    protected List<Node> children;

    protected MultiNode(List<Node> children) {
        this.children = children;
    }
}