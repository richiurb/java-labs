package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Cell getCell();
    int getRows();
    int getColumns();
    Result makeMove(Move move, int playersCount);
    boolean isValid(final Move move);
}
