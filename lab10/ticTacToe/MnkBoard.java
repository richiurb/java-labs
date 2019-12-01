package ticTacToe;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MnkBoard implements Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.H, 'H',
            Cell.B, 'B',
            Cell.E, '.'
    );

    private static final Cell[] TURNS = new Cell[] { Cell.X, Cell.O, Cell.H, Cell.B };

    private final Cell[][] cells;
    private final int columns, rows, k;
    private int turn;
    private int turnsCount;

    public MnkBoard(final int columns, final int rows, final int k) {
        this.columns = columns;
        this.rows = rows;
        this.k = k;

        this.cells = new Cell[rows][columns];

        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }

        turn = 0;
    }

    @Override
    public Cell getCell() {
        return TURNS[turn];
    }
    
    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        if (leftToRightDiag(move) == Result.WIN ||
            rightToLeftDiag(move) == Result.WIN ||
            upToDown(move) == Result.WIN ||
            leftToRight(move) == Result.WIN) {
                return Result.WIN;
        }

        turnsCount++;

        if (turnsCount == rows * columns) {
            return Result.DRAW;
        }

        changeTurn();
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rows
                && 0 <= move.getColumn() && move.getColumn() < columns
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && TURNS[turn] == getCell(); // Что это такое?
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");

        for (int i = 0; i < columns; i++) {
            sb.append(" " + i);
        }
        
        for (int r = 0; r < rows; r++) {
            sb.append("\n");
            sb.append(r);

            for (int c = 0; c < columns; c++) {
                sb.append(" " + SYMBOLS.get(cells[r][c]));
            }
        }

        return sb.toString();
    }

    private void changeTurn() {
        turn = turn == TURNS.length - 1 ? 0 : turn + 1;
    }

    private Result leftToRightDiag(Move move) {
        Cell cellValue = move.getValue();
        int row = move.getRow() - 1;
        int column = move.getColumn() - 1;
        int counter = 1;

        while (row >= 0 && column >= 0 && cells[row--][column--] == cellValue) {
            counter++;
        }

        if (counter == k) {
            return Result.WIN;
        }

        row = move.getRow() + 1;
        column = move.getColumn() + 1;

        while (row <= rows - 1 && column <= columns - 1 && cells[row++][column++] == cellValue) {
            counter++;
        }

        if (counter >= k) {
            return Result.WIN;
        }

        return Result.UNKNOWN;
    }

    private Result rightToLeftDiag(Move move) {
        Cell cellValue = move.getValue();
        int row = move.getRow() - 1;
        int column = move.getColumn() + 1;
        int counter = 1;

        while (row >= 0 && column <= columns - 1 && cells[row--][column++] == cellValue) {
            counter++;
        }

        if (counter == k) {
            return Result.WIN;
        }

        row = move.getRow() + 1;
        column = move.getColumn() - 1;

        while (row <= rows - 1 && column >= 0 && cells[row++][column--] == cellValue) {
            counter++;
        }

        if (counter >= k) {
            return Result.WIN;
        }

        return Result.UNKNOWN;
    }

    private Result upToDown(Move move) {
        Cell cellValue = move.getValue();
        int row = move.getRow() - 1;
        int column = move.getColumn();
        int counter = 1;

        while (row >= 0 && cells[row--][column] == cellValue) {
            counter++;
        }

        if (counter == k) {
            return Result.WIN;
        }

        row = move.getRow() + 1;

        while (row <= rows - 1 && cells[row++][column] == cellValue) {
            counter++;
        }

        if (counter >= k) {
            return Result.WIN;
        }

        return Result.UNKNOWN;
    }

    private Result leftToRight(Move move) {
        Cell cellValue = move.getValue();
        int row = move.getRow();
        int column = move.getColumn() - 1;
        int counter = 1;

        while (column >= 0 && cells[row][column--] == cellValue) {
            counter++;
        }

        if (counter == k) {
            return Result.WIN;
        }

        column = move.getColumn() + 1;

        while (column <= columns - 1 && cells[row][column++] == cellValue) {
            counter++;
        }

        if (counter >= k) {
            return Result.WIN;
        }

        return Result.UNKNOWN;
    }
}
