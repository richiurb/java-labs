package eval.nodes;

public class Multiply extends ExpressionNode {
    public Multiply(BaseNode a, BaseNode b) {
        super(a, b);
    }

    public int getPriority() {
        return 2;
    }

    protected char getOperationChar() {
        return '*';
    }
}