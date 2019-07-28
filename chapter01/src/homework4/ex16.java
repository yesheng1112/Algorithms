package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex16 {

    //返回最小的两个数之间的间隔
    public static void minInterval(double[] arr) {
        double min = Double.MAX_VALUE;
        double num1 = 0.0, num2 = 0.0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                double minInterval = Math.abs(arr[i + 1] - arr[i]);
                if (min > minInterval) {
                    min = minInterval;
                    num1 = arr[i];
                    num2 = arr[i + 1];
                }
            }
        }
        StdOut.printf("closest pair is %8.3f %8.3f minInterval is %8.3f", num1, num2, min);
    }

    //产生随机的数组并排序返回
    public static double[] sourceArr(int N) {
        double[] sourceArr = new double[N];
        for (int i = 0; i < sourceArr.length; i++) {
            sourceArr[i] = StdRandom.uniform(-100.0, 100.0);
        }
        Arrays.sort(sourceArr);
        return sourceArr;
    }

    //打印数组
    public static void printArr(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            StdOut.printf("%8.3f", arr[i]);
            if ((i + 1) % 10 == 0) StdOut.println();
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        double[] arr = sourceArr(20);
        printArr(arr);
        minInterval(arr);
    }
}
