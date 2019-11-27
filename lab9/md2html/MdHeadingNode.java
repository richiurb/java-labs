package md2html;

public class MdHeadingNode extends MdNode {
    public int level;

    public MdHeadingNode(int level) {
        super();
        this.type = MdNodeType.HEADING;
        this.level = level;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        String elem = "h" + level;
        HtmlWriter.appendElement(sb, elem, this);
        sb.append('\n');
    }
}