package homework2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex18 {
    static class Accumulator {
        private double m;
        private double s;
        private int N;

        public void addDataValue(double x) {
            N++;
            s = s + 1.0 * (N - 1) / N * (x - N) * (x - m);
            m = m + (x - m) / N;
        }

        //计算平均值
        public double mean() {
            return m;
        }

        //计算方差
        public double var() {
            return s / (N - 1);
        }

        //计算标准差
        public double stddev() {
            return Math.sqrt(this.var());
        }
    }

    //普通的计算平均值
    public static double mean(int[] arr) {
        double average = 0;
        for (int i = 0; i < arr.length; i++)
            average += arr[i];
        average /= arr.length;
        return average;
    }

    //计算方差
    public static double variance(int[] arr) {
        double average = mean(arr);
        double variance = 0;
        for (int i = 0; i < arr.length; i++)
            variance += Math.pow(arr[i] - average, 2);
        return variance / arr.length;
    }

    //计算标准差
    public static double standardDeviation(int[] arr) {
        return Math.sqrt(variance(arr));
    }

    public static int[] sourceArr() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = StdRandom.uniform(1, 100);
        return arr;
    }

    public static void main(String[] args) {
        Accumulator a = new Accumulator();
        int[] arr = sourceArr();
        for (int i = 0; i < arr.length; i++) {
            a.addDataValue(arr[i]);
        }
        StdOut.println("================= 累加器 ==================");
        StdOut.println("方差 " + a.var());
        StdOut.println("标准差 " + a.stddev());
        StdOut.println("平均值 " + a.mean());
        StdOut.println("================= 普通 ==================");
        StdOut.println("方差 " + variance(arr));
        StdOut.println("标准差 " + standardDeviation(arr));
        StdOut.println("平均值 " + mean(arr));
    }
}
