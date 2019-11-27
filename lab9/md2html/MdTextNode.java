package md2html;

public class MdTextNode extends MdNode {
    public String text;

    public MdTextNode(String text) {
        super();
        this.type = MdNodeType.TEXT;
        this.text = text;
    }

    @Override
    protected boolean needNewLine(boolean isLast) {
        return isLineEnd && !isLast;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        sb.append(text);
    }
}