package ticTacToe;

import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    private static Scanner in;
    static int m, n, k;

    // Добавить обработку ошибок ввода

    public static void main(String[] args) {
        final Game game = new Game(
            true,
            new HumanPlayer(),
            new RandomPlayer());

        int result;

        in = new Scanner(System.in);

        //do {
            
            do {
                System.out.println("Enter \"m\", \"n\", \"k\" for \"m,n,k game\":");
                m = in.nextInt();
                n = in.nextInt();
                k = in.nextInt();
            } while (!isValidInput());


            result = game.play(new MnkBoard(m, n, k));
            System.out.println("Game result: " + result);
        //} while (result != 0);
    }

    private static boolean isValidInput() {
        if (k > 2 && m >= k && n >= k) {
            return true;
        } else {
            System.out.println("m, n or k is incorrect for \"m,n,k game\". Please, try again.");
            return false;
        }
    }
}
