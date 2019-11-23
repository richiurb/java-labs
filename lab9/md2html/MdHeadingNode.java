package md2html;

public class MdHeadingNode extends MdNode {
    public int level;

    public MdHeadingNode(int level) {
        super();
        this.type = MdNodeType.HEADING;
        this.level = level;
    }
}