package md2html;

public class MdTextNode extends MdNode {
    public String text;

    public MdTextNode(String text) {
        super();
        this.type = MdNodeType.TEXT;
        this.text = text;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        sb.append(text);

        if (isLineEnd && !isLast) {
            sb.append('\n');
        }
    }
}