package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex17 {

    public static void maxInterval(double[] arr) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        StdOut.printf("fairest Pair : %8.3f %8.3f", max, min);
    }

    //生成随机数组
    public static double[] sourceArr(int N) {
        double[] sourceArr = new double[N];
        for (int i = 0; i < sourceArr.length; i++) {
            sourceArr[i] = StdRandom.uniform(-100.0, 100.0);
        }
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
        maxInterval(arr);
    }
}
