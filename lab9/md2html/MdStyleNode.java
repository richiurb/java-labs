package md2html;

import java.util.*;

public class MdStyleNode extends MdNode {
    private static Map<MdStyleNodeType, String> styleNodesMapping = Map.of(
        MdStyleNodeType.EMPHASIZED, "em",
        MdStyleNodeType.STRONG, "strong",
        MdStyleNodeType.STRIKE, "s",
        MdStyleNodeType.CODE, "code"
    );

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

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        String elem = styleNodesMapping.get(styleType);
        HtmlWriter.appendElement(sb, elem, this);

        if (isLineEnd && (!isLast || isInLink())) {
            sb.append('\n');
        }
    }
}