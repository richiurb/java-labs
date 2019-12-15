package expression.parser;

public class ExpressionScanner {
    private String source;
    private int index;
    private char currentChar;

    public ExpressionScanner(final String source) {
        this.source = source;
    }

    public boolean hasNext() {
        for (; index < source.length(); index++) {
            currentChar = source.charAt(index);

            if (!Character.isWhitespace(currentChar)) {
                return true;
            }
        }

        return false;
    }

    public char next() {
        char charToReturn = currentChar;
        currentChar = '\0';
        index++;

        return charToReturn;
    }

    public RuntimeException error(final String message) {
        return new RuntimeException(index + ": " + message);
    }
}