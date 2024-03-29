package markup;

import java.util.*;

public class Strong extends ContainerNode {
    public Strong(List<Node> children) {
        super(children);
    }

    @Override
    protected String getMarkdownElement() {
        return "__";
    }

    @Override
    protected String getHtmlElement() {
        return "strong";
    }
}