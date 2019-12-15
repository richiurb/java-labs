package expression;

public abstract class BinaryExpression implements CommonExpression {
    protected CommonExpression a;
    protected CommonExpression b;

    public BinaryExpression(CommonExpression a, CommonExpression b) {
        this.a = a;
        this.b = b;
    }

    public abstract int getPriority();
    protected abstract String getOperationString();
    protected abstract int evaluateExpression(int val1, int val2);

    protected boolean isOrdered() {
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluateExpression(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        BinaryExpression otherExpr = (BinaryExpression)other;
        
        return otherExpr.a.equals(a)
            && otherExpr.b.equals(b);
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", a.toString(), getOperationString(), b.toString());
    }

    @Override
    public String toMiniString() {
        String str1 = getExpressionMiniString(a,
            !(a instanceof BinaryExpression) || removeBracketsLeft((BinaryExpression) a));

        String str2 = getExpressionMiniString(b,
            !(b instanceof BinaryExpression) || removeBracketsRight((BinaryExpression) b));
        
        return String.format("%s %s %s", str1, getOperationString(), str2);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    private String getExpressionMiniString(Expression expr, boolean removeBrackets) {
        return removeBrackets ? expr.toMiniString() : String.format("(%s)", expr.toMiniString());
    }

    private boolean removeBracketsLeft(BinaryExpression expr) {
        return expr.getPriority() >= getPriority();
    }

    private boolean removeBracketsRight(BinaryExpression expr) {
        return expr.isOrdered()
            ? expr.getPriority() > getPriority()
            : expr.getPriority() == getPriority() && !isOrdered();
    }
}