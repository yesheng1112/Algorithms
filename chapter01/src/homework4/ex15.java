package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex15 {
    static class TwoSumFaster {
        /*
         * 思路 :
         *
         * 对于已排序数组，右边的值都比左侧大，所以如果
         * a[left] + a[right] > 0
         * 那么，此时无论这个左侧索引的右边选择什么值来与 a[right] 相加，
         * 都不可能让和小于或等于 0，此时我们让右侧索引减1
         * 同样的，如果 a[left] + a[right] < 0
         * 说明无论在这个右侧索引的左边选择什么值，都不能让和大于等于0，
         * 因为都它们都小于 a[right],此时我们让左侧索引加1
         *
         * 进一步优化，如果最小元素都大于0，全部都是正数，那也就不可能找得到 TwoSum 了
         * 所以第一步，判断首元素是否大于等于0
         */
        public static int count(int[] a) {
            //最小值为大于0，就没有必要比较了
            if (a == null || a.length == 0 || a[0] > 0) return 0;
            int cnt = 0, N = a.length, lo = 0, hi = N - 1;
            while (lo < hi) {
                if (a[lo] + a[hi] < 0) lo++;
                else if (a[lo] + a[hi] > 0) hi--;
                else {
                    if (a[lo] == a[lo+1])
                        lo++;
                    else if (a[hi] == a[hi-1])
                        hi--;
                    else {
                        lo++;
                        hi--;
                    }
                    cnt++;
                }
            }
            return cnt;
        }
    }

    static class ThreeSumFaster {
        public static int count(int[] a) {
            if (a == null || a.length == 0 || a[0] > 0) return 0;
            int cnt = 0, N = a.length, lo = 0, hi = 0;
            for (int i = 0; i < N; i++) {
                lo = i + 1;
                hi = N - 1;
                while (lo <= hi) {
                    if (a[lo] + a[hi] > -a[i]) hi--;
                    else if (a[lo] + a[hi] < -a[i]) lo++;
                    else {
                        lo++;
                        hi--;
                        cnt++;
                    }
                }
            }
            return cnt;
        }
    }

    /*
     * 产生已排序随机数组
     */
    public static int[] sourceArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = StdRandom.uniform(-100, 100);
        Arrays.sort(arr);
        return arr;
    }

    /*
     * 打印数组
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
        int[] arr1 = sourceArr(30);
        int[] arr = {-3, -2, -1, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        printArray(arr);
        StdOut.println("two sum count : " + TwoSumFaster.count(arr) + "\n");
        printArray(arr1);
        StdOut.println("three sum count : " + ThreeSumFaster.count(arr1));
    }
}
