package md2html;

public class MdLinkNode extends MdNode {
    public StringBuilder url;

    // Stage 0 - обработка текста ссылки из квадратных скобок
    // Stage 1 - обработка URL из круглых скобок
    public int stage;

    public MdLinkNode() {
        super();
        this.type = MdNodeType.LINK;
        url = new StringBuilder();
    }
}