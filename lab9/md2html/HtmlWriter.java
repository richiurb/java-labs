package md2html;

public class HtmlWriter {
    public static void appendElement(StringBuilder sb, String elem, MdNode node) {
        appendElement(sb, elem, null, node);
    }

    public static void appendElement(StringBuilder sb, String elem, String attribute, MdNode node) {
        String attributeString = attribute == null || attribute.isBlank() ? "" : " " + attribute;
        sb.append(getOpeningTag(elem + attributeString));
        sb.append(node.getHtml());
        sb.append(getClosingTag(elem));
    }

    private static String getOpeningTag(String elem) {
        return "<" + elem + ">";
    }

    private static String getClosingTag(String elem) {
        return "</" + elem + ">";
    }
}