package expression;

public class Variable implements Expression, TripleExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x) {
        return name == "x" ? x : 0;
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
        return 3;
    }

    public boolean equals(Object other) {
        return ((Variable)other).name == name;
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return name.hashCode();
    }
}