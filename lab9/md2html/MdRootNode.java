package md2html;

public class MdRootNode extends MdNode {
    public MdRootNode() {
        super();
        this.type = MdNodeType.ROOT;
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
    }
}