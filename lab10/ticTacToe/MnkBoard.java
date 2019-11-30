package ticTacToe;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MnkBoard implements Board, Position {
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
    public Position getPosition() {
        return this;
    }

    // Position interface
    @Override
    public Cell getCell() {
        return TURNS[turn];
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        int inDiag1 = 0;
        int inDiag2 = 0;
        int empty = 0;

        for (int u = 0; u < rows; u++) {
            int inRow = 0;
            int inColumn = 0;

            for (int v = 0; v < columns; v++) {
                if (cells[u][v] == TURNS[turn]) {
                    inRow++;
                }

                if (cells[v][u] == TURNS[turn]) {
                    inColumn++;
                }

                if (cells[u][v] == Cell.E) {
                    empty++;
                }
            }

            if (inRow == k || inColumn == k) {
                return Result.WIN;
            }

            if (cells[u][u] == TURNS[turn]) {
                inDiag1++;
            }

            if (cells[u][columns - 1 - u] == TURNS[turn]) {
                inDiag2++;
            }
        }

        if (inDiag1 == k || inDiag2 == k) {
            return Result.WIN;
        }
        
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == TURNS.length - 1 ? 0 : turn + 1;
        return Result.UNKNOWN;
    }

    // Position interface
    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rows
                && 0 <= move.getColumn() && move.getColumn() < columns
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && TURNS[turn] == getCell(); // Что это такое?
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
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
}
