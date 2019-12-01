package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Cell getCell();
    Cell getCell(final int r, final int c);
    int getRows();
    int getColumns();
    Result makeMove(Move move);
    boolean isValid(final Move move);
}
