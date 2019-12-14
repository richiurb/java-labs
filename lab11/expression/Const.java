package expression;

public class Const implements PrioritizedExpression {
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
        return 4;
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        
        return ((Const)other).value == value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public int hashCode() {
        return value;
    }
}