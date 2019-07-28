package homework1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex30 {

    /**
     * 使用2300多年前的欧几里得算法求解两数的最大公约数
     * 数一和数二的最大公约数等于数二和数一和数二的余数的最大公约数
     *
     * @param p 数一
     * @param q 数二
     * @return 最大公约数
     */
    public static int Euclid(int p, int q) {
        if (q == 0)
            return p;
        int r = p % q;
        return Euclid(q, r);
    }

    public static void main(String[] args) {
        int[][] a = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25}};
        boolean[][] b = new boolean[5][5];
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int result = Euclid(i, j);
                if (result == 1) b[i][j] = true;
                else b[i][j] = false;
            }
        }
        for (boolean[] booleans : b) {
            for (boolean aBoolean : booleans) {
                StdOut.print(aBoolean + " ");
            }
            StdOut.println();
        }
    }
}
