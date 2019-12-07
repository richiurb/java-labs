package eval.nodes;

public class Variable extends BaseNode {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}