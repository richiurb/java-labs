package markup;

import java.util.*;

public abstract class ChildrenNode extends MultiNode {
    protected ChildrenNode(List<Node> children) {
        super(children);
    }

    protected abstract String getHtmlElement();
    protected abstract String getMarkdownElement();

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(String.format("<%s>", getHtmlElement()));

        for (Node node : children) {
            node.toHtml(sb);
        }

        sb.append(String.format("</%s>", getHtmlElement()));
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(getMarkdownElement());

        for (Node node : children) {
            node.toMarkdown(sb);
        }

        sb.append(getMarkdownElement());
    }
}