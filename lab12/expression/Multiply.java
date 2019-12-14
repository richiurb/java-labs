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
        return 2;
    }

    @Override
    protected String getOperationSymbol() {
        return "*";
    }
}