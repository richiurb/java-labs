package expression;

public abstract class BinaryExpression implements CommonExpression {
    protected CommonExpression a;
    protected CommonExpression b;

    public BinaryExpression(CommonExpression a, CommonExpression b) {
        this.a = a;
        this.b = b;
    }

    public abstract int getPriority();
    protected abstract char getOperationChar();
    protected abstract int evaluateExpression(int val1, int val2);

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
        return String.format("(%s %c %s)", a.toString(), getOperationChar(), b.toString());
    }

    @Override
    public String toMiniString() {
        /*String str1 = getMiniString(a, -2);
        String str2 = getMiniString(b, 0);*/

        String str1 = !removeLeftBrackets(a) 
            ? String.format("(%s)", a.toMiniString()) 
            : a.toMiniString();

        String str2 = !removeRightBrackets(b) 
            ? String.format("(%s)", b.toMiniString()) 
            : b.toMiniString();
        
        return String.format("%s %c %s", str1, getOperationChar(), str2);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    private String getMiniString(CommonExpression expr, int priorityShift) {
        int expressionPriority = expr.getPriority();
        int currentPriority = getPriority() + priorityShift;

        return expressionPriority < currentPriority
            || (expressionPriority - 1 <= currentPriority && expr.isOrdered()) 
            ? String.format("(%s)", expr.toMiniString()) 
            : expr.toMiniString();
    }

    private boolean removeLeftBrackets(CommonExpression expr) {
        if (!(expr instanceof BinaryExpression)) {
            return true;
        }

        return 
            expr.getPriority() > getPriority()
            || (expr.getPriority() == getPriority() && expr.isOrdered() == isOrdered())
            || (expr.getPriority() == getPriority() && expr.isOrdered() && !isOrdered())
            || (expr.getPriority() == getPriority() && !expr.isOrdered() && isOrdered());
    }

    private boolean removeRightBrackets(CommonExpression expr) {
        if (!(expr instanceof BinaryExpression)) {
            return true;
        }

        return 
            (expr.getPriority() == getPriority() && !expr.isOrdered() && !isOrdered())
            || (expr.getPriority() > getPriority() && expr.isOrdered());
    }
}