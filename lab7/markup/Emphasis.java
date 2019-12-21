package markup;

import java.util.*;

public class Emphasis extends ChildrenNode {
    public Emphasis(List<Node> children) {
        super(children);
    }

    @Override
    protected String getMarkdownElement() {
        return "*";
    }

    @Override
    protected String getHtmlElement() {
        return "em";
    }
}