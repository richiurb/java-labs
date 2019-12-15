package expression;

public class RightShift extends BinaryExpression {
    public RightShift(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluateExpression(int val1, int val2) {
        return val1 >> val2;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    protected String getOperationString() {
        return ">>";
    }
}