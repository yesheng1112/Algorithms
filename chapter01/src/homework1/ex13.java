package homework1;

import java.util.Arrays;

public class ex13 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{0, 1, 2, 9, 10}, {3, 4, 5}, {6, 7, 8}, {11}};
        int[] b = new int[a.length];
        //用来记录最大列
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i].length;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i].length > max)
                    max = a[i].length;
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        //记录相同个数的次数
        int[] same = new int[max];
        for (int i = 0; i < same.length; i++) {
            for (int value : b) {
                if (value >= i + 1)
                    same[i]++;
            }
        }
        System.out.println(Arrays.toString(same));
        System.out.println();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < same[i]; j++) {
                System.out.print(a[j][i] + " ");
            }
            System.out.println();
        }
    }
}
