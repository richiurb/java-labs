package markup;

import java.util.*;

public class ListItem extends ChildrenNode {
    public ListItem(List<Node> children) {
        super(children);
    }

    @Override
    protected String getMarkdownElement() {
        return "";
    }

    @Override
    protected String getHtmlElement() {
        return "li";
    }
}