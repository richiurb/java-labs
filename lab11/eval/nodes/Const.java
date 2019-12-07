package eval.nodes;

public class Const extends BaseNode {
    private int value;

    public Const(int value) {
        this.value = value;
    }

    public String toString() {
        return Integer.toString(value);
    }
}