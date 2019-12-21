package markup;

public interface Node {
    void toMarkdown(StringBuilder sb);
    void toHtml(StringBuilder sb);
}