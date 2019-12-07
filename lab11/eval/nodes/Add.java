package eval.nodes;

public class Add extends ExpressionNode {
    public Add(BaseNode a, BaseNode b) {
        super(a, b);
    }

    public int getPriority() {
        return 0;
    }

    protected char getOperationChar() {
        return '+';
    }
}