package md2html;

public class MdParagraphNode extends MdNode {
    public MdParagraphNode() {
        super();
        this.type = MdNodeType.PARAGRAPH;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        String elem = "p";
        HtmlWriter.appendElement(sb, elem, this);
        sb.append('\n');
    }
}