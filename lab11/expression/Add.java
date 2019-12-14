package expression;

public class Add extends BinaryExpression {
    public Add(PrioritizedExpression a, PrioritizedExpression b) {
        super(a, b);
    }

    @Override
    public int evaluateExpression(int val1, int val2) {
        return val1 + val2;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    protected char getOperationChar() {
        return '+';
    }
}