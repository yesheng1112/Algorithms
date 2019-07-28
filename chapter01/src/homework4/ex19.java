package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public class ex19 {
    /*
     * 矩阵局部最小元素
     *
     * 思路 :
     *
     * 首先我们不断用二分法缩小行索引，在每次二分命中的行中，找到一个最小的元素，这时我们得到一个列索引
     * 可以知道，由于这个元素已经是该行最小，因此肯定满足左侧右侧都大于它
     * a[?][min] < a[?][min + 1] && a[?][min] > a[?][min - 1]
     * 此时我们再检测上下是否大于该元素，如果是，那么此元素就是局部最小值
     * 如果不是，那么我们挑选上下元素中小的那一侧范围，继续二分，最后可能找到，也可能找不到
     * 但起码我们保证了算法的运行时间为 O(N)
     *
     */
    static class Result {
        int row, column, value;

        //行，列，值
        Result(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        //重写toString方法，打印行列的元素的值
        public String toString() {
            return String.format("[%d,  %d] = %d\n", row, column, value);
        }
    }

    /**
     * 返回局部矩阵最小元素 a[i][j] < a[i+1][j],a[i][j] < a[i][j+1] a[i][j]< a[i-1][j] a[i][j] < a[i][j-1]
     * 中间的元素比周围的四个元素都小
     *
     * @param a 矩阵
     * @return 局部矩阵最小元素
     */
    private static Result localMinimumOfMatrix(int[][] a) {
        //多少行
        int N = a.length;
        int lo = 0, hi = N - 1, midRow;
        while (lo <= hi) {
            midRow = (lo + hi) >> 1;
            //返回这一行的最小元素
            int minColIndex = minimumColumnIndex(a[midRow]);
            if (midRow == 0) {
                if (a[midRow][minColIndex] < a[midRow + 1][minColIndex])
                    return new Result(midRow, minColIndex, a[midRow][minColIndex]);
                return null;
            }

            if (midRow == N - 1) {
                if (a[midRow][minColIndex] < a[midRow - 1][minColIndex])
                    return new Result(midRow, minColIndex, a[midRow][minColIndex]);
                return null;
            }

            //满足条条件的元素
            if (a[midRow - 1][minColIndex] > a[midRow][minColIndex] &&
                    a[midRow + 1][minColIndex] > a[midRow][minColIndex]) {
                return new Result(midRow, minColIndex, a[midRow][minColIndex]);
            }

            //不满足进行移动位置
            if (a[midRow - 1][minColIndex] < a[midRow + 1][minColIndex]) {
                hi = midRow - 1;
            } else {
                lo = midRow + 1;
            }
        }
        return null;
    }

    /**
     * 获取一行中最小元素的列索引
     *
     * @param a 传入一行元素
     * @return 返回这行最小的一个元素
     */
    private static int minimumColumnIndex(int[] a) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
                index = i;
            }
        }
        return index;
    }

    /*
     * 生成 N * N 个无重复的整数
     */
    public static int[][] sourceArr(int N) {
        Set<Integer> set = new HashSet<>();
        int[][] a = new int[N][];
        for (int i = 0; i < N; i++) {
            a[i] = new int[N];
            for (int j = 0; j < N; j++) {
                int random = StdRandom.uniform(1, 150);
                //当生成的数不包含在集合中
                while (set.contains(random))
                    random = StdRandom.uniform(1, 150);
                set.add(random);
                a[i][j] = random;
            }
        }
        return a;
    }

    /*
     * 打印数组，并打印行索引和列索引
     */
    private static void printArray(int[][] arr) {
        StdOut.printf("%-5s", " ");
        for (int i = 0; i < arr[0].length; i++) {
            StdOut.printf("%-4d", i);
        }
        StdOut.println("\n");
        for (int i = 0; i < arr.length; i++) {
            StdOut.printf("%-3d  ", i);
            for (int j = 0; j < arr[i].length; j++)
                StdOut.printf("%-4d", arr[i][j]);
            StdOut.println();
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int[][] a = sourceArr(6);
        printArray(a);
        StdOut.println(localMinimumOfMatrix(a));
    }
}
