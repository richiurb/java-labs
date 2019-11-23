package md2html;

public class MdLinkNode extends MdNode {
    public StringBuilder url;

    // Stage 0 - processing link text found in square brackets
    // Stage 1 - processing URL found in round brackets
    public int stage;

    public MdLinkNode() {
        super();
        this.type = MdNodeType.LINK;
        url = new StringBuilder();
    }
}