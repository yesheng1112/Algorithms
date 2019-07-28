package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex14 {
    /*
     * 这里直接使用暴力解法则时间为 C(n, 4) = N(N - 1)(N - 2)(N - 3)/24 ~N^4
     * 通过改进最后一个循环，效率变为 ~N^3 * log(N)
     *
     */

    /**
     * 二分查找
     *
     * @param key 查找的数
     * @param a   被查找的数组
     * @return 查到的数的最小下标
     */
    public static int binarySearchByMini(int key, int[] a) {
        int lo = 0, hi = a.length - 1, mid;
        while (lo < hi) {
            mid = (int) Math.ceil((lo + hi) / 2.0);
            if (a[mid] < key)
                lo = mid;
            else
                hi = mid - 1;
        }
        if (a[lo] == key) return lo;
        return ++lo == a.length || a[lo] != key ? -1 : lo;
    }

    static int binarySearchByMaxi(int key, int[] numbers) {
        int lo = 0, hi = numbers.length - 1, mid = 0;
        while (lo < hi) {
            mid = (lo + hi) >> 1;
            if (numbers[mid] > key)
                hi = mid;
            else
                lo = mid + 1;
        }
        // lo == numbers.length - 1
        if (numbers[lo] == key) return lo;
        // lo != numbers.length - 1
        return --lo < 0 || numbers[lo] != key ? -1 : lo;
    }

    static class FourSum {
        public static int count(int[] a) {
            int cnt = 0, N = a.length, index, max;
            Arrays.sort(a);
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++)
                        if ((index = binarySearchByMini(-(a[i] + a[j] + a[k]), a)) > k) {
                            if ((max = binarySearchByMaxi(-(a[i] + a[j] + a[k]), a)) > index) {
                                cnt += max - index + 1;
                                for (int l = 0; l < max - index + 1; l++) {
                                    StdOut.printf("a[%d] = %d a[%d] = %d a[%d] = %d a[%d] = %d\n", i, a[i], j, a[j], k, a[k], index + l, a[index + l]);
                                }
                            } else {
                                cnt++;
                                StdOut.printf("a[%d] = %d a[%d] = %d a[%d] = %d a[%d] = %d\n", i, a[i], j, a[j], k, a[k], index, a[index]);
                            }
                        }
            return cnt;
        }
    }

    /*
     * generate a sorted random array
     */
    public static int[] sourceArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = StdRandom.uniform(-100, 100);
        Arrays.sort(arr);
        return arr;
    }

    /*
     * print array
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if ((i + 1) % 10 == 0)
                StdOut.printf("%5d\n", arr[i]);
            else
                StdOut.printf("%5d", arr[i]);
        StdOut.println();
    }

    public static void main(String[] args) {
//        int[] a = sourceArr(10);
        int[] a = {-2, -1, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3};
        printArray(a);
        StdOut.println("four sum is " + FourSum.count(a));
    }
}

