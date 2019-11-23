package md2html;

public class MdStyleNode extends MdNode {
    public MdStyleNodeType styleType;

    public MdStyleNode(MdStyleNodeType styleType) {
        super();
        this.type = MdNodeType.STYLE;
        this.styleType = styleType;
    }
}