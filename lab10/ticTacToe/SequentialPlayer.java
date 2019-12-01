package ticTacToe;

import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    @Override
    public Move move(int rows, int columns, Cell cell, String boardString, Predicate<Move> isValid) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                final Move move = new Move(r, c, cell);
                
                if (isValid.test(move)) {
                    return move;
                }
            }
        }

        throw new IllegalStateException("No valid moves");
    }
}
