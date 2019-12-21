package markup;

import java.util.*;

public class OrderedList extends ContainerNode {
    public OrderedList(List<Node> children) {
        super(children);
    }

    @Override
    protected String getMarkdownElement() {
        return "";
    }

    @Override
    protected String getHtmlElement() {
        return "ol";
    }
}