package homework1;

import java.util.Arrays;

public class ex15 {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 3, 7, 8};
        int[] histogram = histogram(a, 5);
        System.out.println(Arrays.toString(histogram));
    }

    public static int[] histogram(int[] a, int M) {
        int[] arr = new int[M];
        for (int value : a) {
            if (value < M)
                arr[value]++;
        }
        return arr;
    }
}
