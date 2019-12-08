package expression;

public class Main {
    public static void main(String[] args) {
        final Variable x = new Variable("x");

        int result = new Add(
            new Subtract(
                new Multiply(x, x),
                new Multiply(new Const(2), x)
            ),
            new Const(1)
        ).evaluate(Integer.parseInt(args[0]));
        System.out.println(result);
    }
}