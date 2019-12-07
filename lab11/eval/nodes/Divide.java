package eval.nodes;

public class Divide extends ExpressionNode {
    public Divide(BaseNode a, BaseNode b) {
        super(a, b);
    }

    public int getPriority() {
        return 2;
    }

    protected char getOperationChar() {
        return '/';
    }
}