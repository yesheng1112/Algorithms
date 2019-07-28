package homework1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex39 {
    /*
     * 二分查找
     */
    public static int binarySearch(int key, int[] arr) {
        int l = 0, h = arr.length - 1, mid = 0;
        while (l <= h) {
            mid = (l + h) / 2;
            if (key < arr[mid]) h = mid - 1;
            else if (key > arr[mid]) l = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * 生成给定长度数组
     *
     * @param N 指定的长度
     * @return 生成的数组
     */
    public static int[] source(int N) {
        int[] result = new int[N];
        for (int i = 0; i < result.length; i++) {
            //生成随机的六位数
            result[i] = StdRandom.uniform(100000, 1000000);
        }
        Arrays.sort(result);
        return result;
    }

    public static int count(int[] num1, int[] num2) {
        int count = 0;
        for (int i = 0; i < num1.length; i++) {
            if (binarySearch(num1[i], num2) != -1)
                count++;
        }
        return count;
    }

    /*
     * 有序数组寻找共同元素算法
     */
    public static int commonCount(int[] arr1, int[] arr2) {
        int count = 0;
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) i++;
            else if (arr2[j] < arr1[i]) j++;
            else {
                i++;
                j++;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        /*int N = 1000;
        int T = 10;
        double average = 0;
        for (int i = 0; i < 4; i++) {
            for(int loop = 0; loop < T; loop++)
                average += (double)count(source(N), source(N));
            average /= T;
            StdOut.println("N = " + N + "  " + T + " 次实验平均值 : " + average);
            N *= 10;
        }*/
        int N = 10000;
        int[] num1 = {1,2,3,4,4,4};
        int[] num2 = {3,3,3,4,4,5};

        StdOut.println(Arrays.toString(num1));
        StdOut.println(Arrays.toString(num2));

        long start = System.currentTimeMillis();
        int count = count(num1, num2);
        long end = System.currentTimeMillis();
        StdOut.println("自己的比较方法:" + (end - start) + " count:" + count);

        start = System.currentTimeMillis();
        count = commonCount(num1, num2);
        end = System.currentTimeMillis();
        StdOut.println("别人的比较方法:" + (end - start) + " count:" + count);
    }
}
