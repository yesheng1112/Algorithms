package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex23 {
    /*
     * 思路 :
     *
     * 和二分查找一样，只是二分查找命中条件是相等，
     * 而该题只要求命中元素和目标元素的距离在可接受范围内，就算命中
     *
     */
    private static int binarySearch(double[] a, double key) {
        int N = a.length;
        int lo = 0, hi = a.length;
        double threshold = 1.0 / (N * N);
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            double differ = Math.abs(a[mid] - key);
            //在误差接受的范围之内
            if (differ <= threshold) return mid;
            else if (a[mid] > key) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    /*
     * 产生已排序随机数组
     */
    public static double[] sourceArr(int N) {
        double[] arr = new double[N];
        for (int i = 0; i < N; i++) {
            int p = StdRandom.uniform(1, 10);
            int q = StdRandom.uniform(1, 20);
            while (q <= p)
                q = StdRandom.uniform(1, 20);
            arr[i] = p * 1.0 / q;
        }

        Arrays.sort(arr);
        return arr;
    }

    /*
     * 打印数组
     */
    public static void printArray(double[] arr) {
        for (int i = 0; i < arr.length; i++)
            if ((i + 1) % 10 == 0)
                StdOut.printf("%2.3f\n", arr[i]);
            else
                StdOut.printf("%2.3f ", arr[i]);
        StdOut.println();
    }

    public static void main(String[] args) {
        double[] arr = sourceArr(10);
        printArray(arr);
        StdOut.println("result is " + binarySearch(arr, 4.0 / 7));
    }
}
