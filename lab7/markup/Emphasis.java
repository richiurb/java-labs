package markup;

import java.util.*;

public class Emphasis extends ContainerNode {
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