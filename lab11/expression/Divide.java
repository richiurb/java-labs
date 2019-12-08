package expression;

public class Divide extends BinaryExpression {
    public Divide(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    public int evaluateExpression(int val1, int val2) {
        return val1 / val2;
    }

    public int getPriority() {
        return 2;
    }

    protected char getOperationChar() {
        return '/';
    }
}