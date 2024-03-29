package markup;

import java.util.*;

public class Strikeout extends ContainerNode {
    public Strikeout(List<Node> children) {
        super(children);
    }

    @Override
    public String getMarkdownElement() {
        return "~";
    }

    @Override
    public String getHtmlElement() {
        return "s";
    }
}