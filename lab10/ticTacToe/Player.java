package ticTacToe;

import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Player {
    Move move(int rows, int columns, Cell cell, String boardString, Predicate<Move> isValid);
}
