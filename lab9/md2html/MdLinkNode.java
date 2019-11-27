package md2html;

public class MdLinkNode extends MdNode {
    private final String htmlElement = "a";
    public StringBuilder url;

    // Stage 0 - processing link text found in square brackets
    // Stage 1 - processing URL found in round brackets
    public int stage;

    public MdLinkNode() {
        super();
        this.type = MdNodeType.LINK;
        url = new StringBuilder();
    }

    @Override
    protected boolean needNewLine(boolean isLast) {
        return isLineEnd && !isLast;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        HtmlWriter.appendElement(sb, htmlElement, "href='" + url + "'", this);
    }
}