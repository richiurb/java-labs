package markup;

import java.util.*;

public class UnorderedList extends ContainerNode {
    public UnorderedList(List<Node> children) {
        super(children);
    }

    @Override
    protected String getMarkdownElement() {
        return "";
    }

    @Override
    protected String getHtmlElement() {
        return "ul";
    }
}