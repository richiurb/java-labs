import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class ReverseSort {

    public static void main(String[] args) {
        long[][] num = new long[1][];
        int i = 0;

        try {
            CustomScanner sc = new CustomScanner(System.in);

            try {
                long[] copy = new long[1];

                while (sc.hasNextLine()) {     
                    int j = 1;
                    long sum = 0;

                    while (sc.hasNextInt()) {
                        if (j == copy.length) {
                            copy = Arrays.copyOf(copy, 2 * j);
                        }
                        copy[j] = sc.nextInt();
                        sum += copy[j++];
                    }
                    copy[0] = sum;
                    Arrays.sort(copy, 1, j);
                    if (i == num.length) {
                        num = Arrays.copyOf(num, 2 * i);
                    }
                    num[i++] = Arrays.copyOf(copy, j);
                }
            } finally {
                sc.close();
            }
        } catch (IOException ex) {
            System.err.println("Input error:(");
            return;
        }
        num = Arrays.copyOf(num, i);
        Arrays.sort(num, Comparator.comparingLong(arr -> arr[0]));

        for (i -= 1; i >= 0; i--) {
            if (num[i].length > 0) {
                for (int k = num[i].length  - 1; k > 0; k--) {
                        System.out.print(num[i][k] + " ");
                }
            }

            System.out.println();
        }
    }
}