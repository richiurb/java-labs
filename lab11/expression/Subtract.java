package expression;

public class Subtract extends BinaryExpression {
    public Subtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluateExpression(int val1, int val2) {
        return val1 - val2;
    }

    @Override
    public boolean isOrdered() {
        return true;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    protected String getOperationString() {
        return "-";
    }
}