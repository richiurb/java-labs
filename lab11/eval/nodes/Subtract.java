package eval.nodes;

public class Subtract extends ExpressionNode {
    public Subtract(BaseNode a, BaseNode b) {
        super(a, b);
    }

    public int getPriority() {
        return 1;
    }

    protected char getOperationChar() {
        return '-';
    }
}