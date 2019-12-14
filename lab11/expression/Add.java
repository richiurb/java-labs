package expression;

public class Add extends BinaryExpression {
    public Add(PrioritizedExpression a, PrioritizedExpression b) {
        super(a, b);
    }

    public int evaluateExpression(int val1, int val2) {
        return val1 + val2;
    }

    public int getPriority() {
        return 0;
    }

    protected char getOperationChar() {
        return '+';
    }
}