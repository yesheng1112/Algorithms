package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class ex9 {
    /*
     * ThreeSum Doubling ratio experiment
     */
    static class ThreeSum {
        public static int count(int[] a) {
            int cnt = 0;
            int N = a.length;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++)
                        if (a[i] + a[j] + a[k] == 0)
                            cnt++;
            return cnt;
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        public static double timeTrial(int N) {
            Stopwatch timer = new Stopwatch();
            int[] arr = new int[N];
            for (int i = 0; i < N; i++)
                arr[i] = StdRandom.uniform(-10000, 10000);
            ThreeSum.count(arr);
            return timer.elapsedTime();
        }

        public static void doublingRatioExperiment() {
            double prev = timeTrial(100);
            for (int N = 200; true; N += N) {
                double time = timeTrial(N);
                StdOut.printf("%6d %7.1f", N, time);
                StdOut.printf("%5.1f\n", time / prev);
                prev = time;
            }
        }
    }

    public static void main(String[] args) {
        /*
         *
         * T(N0) -> T0
         * T(2 * N0) -> 2^b * T0
         * T(2^2 * N0) -> 2^(2b) * T0
         * T(2^3 * N0) -> 2^(3b) * T0
         * T(2^r * N0) -> 2^(r * b) * T0
         *
         * T(r) -> 2^(lgN/N0 * b) * T0
         * T(r) -> 2^lg((N/N0)^b) * T0
         * T(r) -> (N/N0)^b * T0
         *
         * 所以我们可以得到总耗时为 (N/N0)^b * T0
         *
         * for example
         *
         * N0 = 4000 T0 = 3.7
         * N = 16000 T ~ 2^6 * 3.7 = 236.8 ~ 240.3
         *
         */
        StdOut.println("\tN\ttime\ttime/preTime");
        ThreeSum.doublingRatioExperiment();
    }
}