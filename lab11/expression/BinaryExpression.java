package expression;

public abstract class BinaryExpression implements Expression, TripleExpression {
    protected TripleExpression a;
    protected TripleExpression b;

    public BinaryExpression(TripleExpression a, TripleExpression b) {
        this.a = a;
        this.b = b;
    }

    protected abstract char getOperationChar();
    protected abstract int getPriority();
    protected abstract int evaluateExpression(int val1, int val2);

    public int evaluate(int x, int y, int z) {
        return evaluateExpression(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        BinaryExpression otherExpr = (BinaryExpression)other;
        
        return otherExpr.a.equals(a)
            && otherExpr.b.equals(b);
    }

    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    public int evaluate(int x, int y) {
        return evaluate(x, y, 0);
    }

    public String toString() {
        return String.format("(%s %c %s)", a.toString(), getOperationChar(), b.toString());
    }

    public String toMiniString() {
        String stra = a.getPriority() < getPriority() 
            ? String.format("(%s)", a.toMiniString()) 
            : a.toMiniString();

        String strb = b.getPriority() <= getPriority() 
            ? String.format("(%s)", b.toMiniString()) 
            : b.toMiniString();
        
        return String.format("%s %c %s", stra, getOperationChar(), strb);
    }

    public int hashCode() {
        return toString().hashCode();
    }
}