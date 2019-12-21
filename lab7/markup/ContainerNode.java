package markup;

import java.util.*;

public abstract class ContainerNode extends MultiNode {
    protected ContainerNode(List<Node> children) {
        super(children);
    }

    protected abstract String getHtmlElement();
    protected abstract String getMarkdownElement();

    @Override
    public void toHtml(StringBuilder sb) {
        String htmlElement = getHtmlElement();
        sb.append(String.format("<%s>", htmlElement));

        for (Node node : children) {
            node.toHtml(sb);
        }

        sb.append(String.format("</%s>", htmlElement));
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        String markdownElement = getMarkdownElement();
        sb.append(markdownElement);

        for (Node node : children) {
            node.toMarkdown(sb);
        }

        sb.append(markdownElement);
    }
}