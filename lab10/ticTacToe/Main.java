package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {

    // Добавить обработку ошибок ввода
    // Проверка за О(k)
    // Предотвратить жульничество (Board -> Position -> Board)

    public static void main(String[] args) {
        final Game game = new Game(
            true,
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer());

        int result;

        //do {
            result = game.play(new MnkBoard(5, 5, 4));
            System.out.println("Game result: " + result);
        //} while (result != 0);
    }
}
