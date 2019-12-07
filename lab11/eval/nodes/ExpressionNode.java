package eval.nodes;

public abstract class ExpressionNode extends BaseNode {
    private BaseNode a;
    private BaseNode b;

    public ExpressionNode(BaseNode a, BaseNode b) {
        this.a = a;
        this.b = b;
    }

    protected abstract int getPriority();
    protected abstract char getOperationChar();

    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    public int evaluate(int x, int y) {
        return evaluate(x, y, 0);
    }

    public int evaluate(int x, int y, int z) {
        return 0;
    }

    public boolean equals(ExpressionNode other) {
        return false;
    }

    public String toString() {
        return String.format("(%s %c %s)", a.toString(), getOperationChar(), b.toString());
    }

    public String toMiniString() {
        return null;
    }
}