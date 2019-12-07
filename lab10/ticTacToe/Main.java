package ticTacToe;

import java.util.*;

/*
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        final Game game = new Game(
            true,
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer());

        int rows = inputInt("Input rows count", 3, Integer.MAX_VALUE);
        int columns = inputInt("Input columns count", 3, Integer.MAX_VALUE);
        int k = inputInt("Input K", 3, Math.min(rows, columns));

        int result;

        //do {
            result = game.play(new MnkBoard(rows, columns, k));
            System.out.println("Game result: " + result);
        //} while (result != 0);
    }

    public static int inputInt(String caption, int minValue, int maxValue) {
        while (true) {
            System.out.print(caption + ": ");

            if (sc.hasNextInt()) {
                int value = sc.nextInt();

                if (value >= minValue && value <= maxValue) {
                    return value;
                }
            }

            System.out.println(
                "Input is invalid. Please enter a number between " +
                minValue +
                " and " +
                maxValue);

            sc.nextLine();
        }
    }
}
