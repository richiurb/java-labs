package expression.parser;

public class BaseParser {
    private ExpressionScanner source;
    protected char ch;

    protected void setSource(ExpressionScanner source) {
        this.source = source;
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(char expected) {
        return ch == expected;
    }

    protected void expect(final char c) {
        if (ch != c) {
            throw error("Expected '" + c + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
            nextChar();
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected RuntimeException error(final String message) {
        return source.error(message);
    }
}
