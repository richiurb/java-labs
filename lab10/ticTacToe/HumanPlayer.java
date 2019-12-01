package ticTacToe;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(int rows, int columns, Cell cell, String boardString, Predicate<Move> isValid) {
        while (true) {
            out.println("Position");
            out.println(boardString);
            out.println(cell + "'s move");
            out.println("Enter row and column");

            try {
                final Move move = new Move(in.nextInt(), in.nextInt(), cell);

                if (isValid.test(move)) {
                    return move;
                }

                out.println("Move " + move + " is invalid");
            } catch (InputMismatchException ex) {
                in.next();
                out.println("Input is invalid");
            }
        }
    }
}
