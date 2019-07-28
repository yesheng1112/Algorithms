package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
 * 思路 :
 *
 * 运行时间为 N 说明只能遍历一遍
 * 我们首先用两个索引指向两个有序数组
 *
 * 如果 a[i] > b[j] 说明索引比 j 小的数据都不可能与 a[i] 相等，此时让 j 往后挪
 * 如果 a[i] < b[j] 说明索引比 i 小的数据都不可能与 b[j] 相等，此时让 i 往后挪
 * 如果 a[i] = ab[j] 说明已经找到一个共同元素，那么就让各自的索引分别向后挪一位
 *
 */
public class ex12 {
    /*
     * find common element within O(N)
     */
    public static int countCommon(int[] a, int[] b) {
        if (a == null || b == null)
            throw new NullPointerException();
        int i = 0, j = 0, cnt = 0;
        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) j++;
            else if (a[i] < b[j]) i++;
            else {
                cnt++;
                i++;
                j++;
            }
        }
        return cnt;
    }

    /*
     * generate a sorted random array
     */
    public static int[] sourceArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = StdRandom.uniform(0, 100);
        Arrays.sort(arr);
        return arr;
    }

    /*
     * print array
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if ((i + 1) % 10 == 0)
                StdOut.printf("%3d\n", arr[i]);
            else
                StdOut.printf("%3d", arr[i]);
        StdOut.println();
    }

    public static void main(String[] args) {
        int[] a = sourceArr(100);
        int[] b = sourceArr(100);
        printArray(a);
        printArray(b);
        StdOut.println("common count is " + countCommon(a, b));
    }
}