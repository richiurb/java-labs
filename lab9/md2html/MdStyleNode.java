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

    @Override
    protected boolean needNewLine(boolean isLast) {
        return isLineEnd && (!isLast || isInLink());
    }

    protected void writeHtml(StringBuilder sb, boolean isLast) {
        HtmlWriter.appendElement(sb, styleNodesMapping.get(styleType), this);
    }
}