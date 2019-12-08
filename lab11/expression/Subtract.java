package expression;

public class Subtract extends BinaryExpression {
    public Subtract(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    public int evaluateExpression(int val1, int val2) {
        return val1 - val2;
    }

    public int getPriority() {
        return 1;
    }

    protected char getOperationChar() {
        return '-';
    }
}