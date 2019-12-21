package markup;

import java.util.*;

public class Paragraph extends MultiNode {
    public Paragraph(List<Node> children) {
        super(children);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (Node node : children) {
            node.toMarkdown(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        for (Node node : children) {
            node.toHtml(sb);
        }
    }
}