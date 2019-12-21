import java.util.Arrays;

public class IntList {
    private final double MULTIPLIER = 1.5;

    private int[] list = new int[1];
    private int size = 0;

    public void add(int value) {
        if (list.length == size) {
            list = Arrays.copyOf(list, (int) Math.ceil(size * MULTIPLIER));
        }

        list[size++] = value;
    }

    public int[] getAll() {
        return Arrays.copyOf(list, size);
    }
}