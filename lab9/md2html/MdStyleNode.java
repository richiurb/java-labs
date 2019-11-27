package md2html;

public class MdStyleNode extends MdNode {
    public MdStyleNodeType styleType;

    public MdStyleNode(MdStyleNodeType styleType) {
        super();
        this.type = MdNodeType.STYLE;
        this.styleType = styleType;
    }

    public boolean isInLink() {
        MdNode node = parent;

        while (node.type != MdNodeType.ROOT) {
            if (node.type == MdNodeType.LINK) {
                return true;
            }

            node = node.parent;
        }

        return false;
    }
}