package markup;

public class Text implements Node {
    public String text;

    public Text(String text) {
        super();
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(text);
    }
}