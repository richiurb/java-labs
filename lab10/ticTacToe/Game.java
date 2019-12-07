package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player[] players;

    public Game(
        final boolean log,
        final Player player1,
        final Player player2) {
            this.log = log;
            this.players = new Player[] { player1, player2 };
    }

    public Game(
        final boolean log,
        final Player player1,
        final Player player2,
        final Player player3) {
            this.log = log;
            this.players = new Player[] { player1, player2, player3 };
    }

    public Game(
        final boolean log,
        final Player player1,
        final Player player2,
        final Player player3,
        final Player player4) {
        this.log = log;
        this.players = new Player[] { player1, player2, player3, player4 };
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                final int result = move(board, i);

                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final int playerIndex) {
        final Move move = players[playerIndex].move(
            board.getRows(), 
            board.getColumns(),
            board.getCell(),
            board.toString(),
            (m) -> board.isValid(m));

        final Result result = board.makeMove(move, players.length);
        final int playerNumber = playerIndex + 1;

        log("Player " + playerNumber + " move: " + move);
        log("Position:\n" + board);
        
        if (result == Result.WIN) {
            log("Player " + playerNumber + " won");
            return playerNumber;
        } else if (result == Result.LOSE) {
            log("Player " + playerNumber + " lose");
            return players.length - playerIndex;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
