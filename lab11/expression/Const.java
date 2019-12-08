package expression;

public class Const implements Expression, TripleExpression {
    private int value;

    public Const(int value) {
        this.value = value;
    }

    public int evaluate(int x) {
        return value;
    }

    public int evaluate(int x, int y, int z) {
        return value;
    }

    public int getPriority() {
        return 3;
    }

    public boolean equals(Object other) {
        return ((Const)other).value == value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public int hashCode() {
        return value;
    }
}