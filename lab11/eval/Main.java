package eval;

import eval.nodes.*;

public class Main {
    public static void main(String[] args) {
        String root = new Subtract(new Multiply(new Const(2), new Variable("x")), new Const(3)).toString();
        System.out.println(root);
    }
}