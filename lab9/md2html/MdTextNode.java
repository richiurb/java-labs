package md2html;

public class MdTextNode extends MdNode {
    public String text;

    public MdTextNode(String text) {
        super();
        this.type = MdNodeType.TEXT;
        this.text = text;
    }
}