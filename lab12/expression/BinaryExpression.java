package expression;

public abstract class BinaryExpression implements CommonExpression {
    protected CommonExpression a;
    protected CommonExpression b;

    public BinaryExpression(CommonExpression a, CommonExpression b) {
        this.a = a;
        this.b = b;
    }

    public abstract int getPriority();
    protected abstract String getOperationSymbol();
    protected abstract int evaluateExpression(int val1, int val2);

    public int evaluate(int x, int y, int z) {
        return evaluateExpression(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    public int evaluate(int x, int y) {
        return evaluate(x, y, 0);
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
        return String.format("(%s %s %s)", a.toString(), getOperationSymbol(), b.toString());
    }

    @Override
    public String toMiniString() {
        String str1 = getMiniString(a, -2);
        String str2 = getMiniString(b, 0);
        
        return String.format("%s %s %s", str1, getOperationSymbol(), str2);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    private String getMiniString(CommonExpression expr, int priorityShift) {
        int expressionPriority = expr.getPriority();
        int currentPriority = getPriority() + priorityShift;

        return expressionPriority < currentPriority
            || (expressionPriority - 1 <= currentPriority 
                && (expressionPriority == 1 || expressionPriority == 3)) 
            ? String.format("(%s)", expr.toMiniString()) 
            : expr.toMiniString();
    }
}