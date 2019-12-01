package ticTacToe;

import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(int rows, int columns, Cell cell, String boardString, Predicate<Move> isValid) {
        while (true) {
            int r = random.nextInt(rows);
            int c = random.nextInt(columns);
            
            final Move move = new Move(r, c, cell);

            if (isValid.test(move)) {
                return move;
            }
        }
    }
}
