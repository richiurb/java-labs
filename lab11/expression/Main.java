package expression;

public class Main {
    public static void main(String[] args) {
        final Variable vx = new Variable("x");
        final Variable vy = new Variable("y");
        final Const c2 = new Const(2);

        CommonExpression a = new Multiply(new Subtract(vy, vx), new Divide(c2, vx));
        CommonExpression b = new Divide(new Subtract(vy, vx), new Multiply(c2, vx));

        int ah = a.hashCode();
        int bh = b.hashCode();

        System.out.println(ah == bh);
    }
}