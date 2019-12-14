package expression;

public class Divide extends BinaryExpression {
    public Divide(PrioritizedExpression a, PrioritizedExpression b) {
        super(a, b);
    }

    @Override
    public int evaluateExpression(int val1, int val2) {
        return val1 / val2;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    protected char getOperationChar() {
        return '/';
    }
}