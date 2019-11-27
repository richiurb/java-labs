package md2html;

public class MdParagraphNode extends MdNode {
    private final String htmlElement = "p";

    public MdParagraphNode() {
        super();
        this.type = MdNodeType.PARAGRAPH;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        HtmlWriter.appendElement(sb, htmlElement, this);
    }
}