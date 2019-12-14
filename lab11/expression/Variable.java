package expression;

public class Variable implements PrioritizedExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }

    public int getPriority() {
        return 4;
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        return ((Variable)other).name == name;
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return name.hashCode();
    }
}