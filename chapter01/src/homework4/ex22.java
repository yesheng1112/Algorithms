package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex22 {
    /*
     * 思路 :
     *
     * 对于斐波那契查找所采取的思路是，先通过 lo hi 确定一个在其间的分割点，
     * 然后确定分割点元素和目标元素的大小关系，之后转向一个子区域，继续用 lo hi
     * 确定分割点，然后重复上述步骤，二分查找的区间范围以 2次幂递减，而斐波那契也是增长率很快的函数
     * 查找范围也减少的很快
     */
    static class FibonacciSearch {

        /**
         * 判读加法操作是否溢出
         *
         * @param x 数1
         * @param y 数2
         * @return 是否溢出
         */
        private static boolean isOverflow(int x, int y) {
            long r = x + y;
            return ((x ^ r) & (y ^ r)) < 0;
        }

        /**
         * 生成斐波那契数列 前两个数的和等于第二个数 1 1 2 3 5 ...
         *
         * @param k 传入k
         * @return 返回斐波那契数列的第k项
         */
        private static int fibo(int k) {
            int prev = 1, next = 1, kk = k;
            while (--k > 0) {
                if (isOverflow(next, prev))
                    StdOut.printf("k = %d next = %d prev = %d 开始溢出\n", kk, next, prev);
                next = next + prev;
                prev = next - prev;
            }
            return next;
        }

        /*
         * 首次加载该 Class 文件时就把斐波纳切前 47 项全部准备好
         */
        private static final int[] fibo;

        static {
            /*
             * 斐波纳切第 46 项 int 溢出
             *       第 93 项 long 溢出
             */
            fibo = new int[46];
            for (int i = 0; i < fibo.length; i++)
                fibo[i] = fibo(i);
        }

        /**
         * 在lo 和 hi 间寻找斐波那契数列分割点
         *
         * @param lo 起始查找的下标
         * @param hi 结束查找的下标
         * @return 分割点
         */
        private static int splitBetween(int lo, int hi) {
            int fIndex = 0;
            while (fibo[fIndex] <= (hi - lo)) fIndex++;
            return fibo[--fIndex] + lo - 1;
        }


        /**
         * 斐波纳切查找
         * 除了第一次的查找外，其他每次都是二分查找
         *
         * @param key 查找的元素
         * @param arr 被查找的数组
         * @return 查找的下标
         */
        private static int rank(int key, int[] arr) {
            int lo = 0, hi = arr.length - 1, split;
            while (lo < hi) {
                split = splitBetween(lo, hi);
                if (arr[split] > key) hi = split;
                else if (arr[split] < key) lo = split + 1;
                else return split;
            }
            return -1;
        }
    }

    /*
     * 产生已排序随机数组
     */
    public static int[] sourceArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = StdRandom.uniform(0, 100);
        Arrays.sort(arr);
        return arr;
    }

    /*
     * 打印数组
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if ((i + 1) % 10 == 0)
                StdOut.printf("%2d\n", arr[i]);
            else
                StdOut.printf("%2d ", arr[i]);
        StdOut.println();
    }

    public static void main(String[] args) {
        int[] arr = sourceArr(10);
        printArray(arr);
        StdOut.println("斐波纳切查找结果 : " + FibonacciSearch.rank(4, arr));
    }

}