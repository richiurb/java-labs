package md2html;

public class MdHeadingNode extends MdNode {
    private final String htmlElement = "h";
    public int level;

    public MdHeadingNode(int level) {
        super();
        this.type = MdNodeType.HEADING;
        this.level = level;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        HtmlWriter.appendElement(sb, htmlElement + level, this);
    }
}