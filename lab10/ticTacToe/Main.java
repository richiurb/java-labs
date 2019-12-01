package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {

    // Добавить обработку ошибок ввода

    public static void main(String[] args) {
        final Game game = new Game(
            true,
            new HumanPlayer(),
            new RandomPlayer());

        int result;

        //do {
            result = game.play(new MnkBoard(4, 4, 3));
            System.out.println("Game result: " + result);
        //} while (result != 0);
    }
}
