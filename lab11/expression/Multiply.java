package expression;

public class Multiply extends BinaryExpression {
    public Multiply(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluateExpression(int val1, int val2) {
        return val1 * val2;
    }

    @Override
    public int getPriority() {
        // This priority is equal to division priority because of:
        // expected: 2 * (x / 1)
        // actual: 2 * x / 1
        return 3;
    }

    @Override
    protected char getOperationChar() {
        return '*';
    }
}