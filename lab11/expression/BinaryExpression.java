package expression;

public abstract class BinaryExpression implements PrioritizedExpression {
    protected PrioritizedExpression a;
    protected PrioritizedExpression b;

    public BinaryExpression(PrioritizedExpression a, PrioritizedExpression b) {
        this.a = a;
        this.b = b;
    }

    public abstract int getPriority();
    protected abstract char getOperationChar();
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
  
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        BinaryExpression otherExpr = (BinaryExpression)other;
        
        return otherExpr.a.equals(a)
            && otherExpr.b.equals(b);
    }

    public String toString() {
        return String.format("(%s %c %s)", a.toString(), getOperationChar(), b.toString());
    }

    public String toMiniString() {
        String str1 = getMiniString(a, -2);
        String str2 = getMiniString(b, 0);
        
        return String.format("%s %c %s", str1, getOperationChar(), str2);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    private String getMiniString(PrioritizedExpression expr, int priorityShift) {
        int expressionPriority = expr.getPriority();
        int currentPriority = getPriority() + priorityShift;

        return expressionPriority < currentPriority
            || (expressionPriority - 1 <= currentPriority && (expressionPriority == 1 || expressionPriority == 3)) 
            ? String.format("(%s)", expr.toMiniString()) 
            : expr.toMiniString();
    }
}