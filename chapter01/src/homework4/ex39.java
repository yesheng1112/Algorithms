package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class ex39 {
    static class ThreeSum {
        static int count(int[] a) {
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++)
                        if (a[i] + a[j] + a[i] == 0)
                            cnt++;
            return cnt;
        }
    }

    static class DoublingRatio {
        static double timeTrial(int N, int M) {
            int[] arr = new int[N];
            for (int i = 0; i < N; i++)
                arr[i] = StdRandom.uniform(-10000, 10000);
            double averageTime = 0;
            for (int i = 0; i < M; i++) {
                Stopwatch timer = new Stopwatch();
                int cnt = ThreeSum.count(arr);
                averageTime += timer.elapsedTime();
            }
            return averageTime / M;
        }

        public static void main(String[] args) {
            StdOut.printf("N = 10, M = 100 TotalTime : %f\n",
                    DoublingRatio.timeTrial(10, 100));
            StdOut.printf("N = 100, M = 100 TotalTime : %f\n",
                    DoublingRatio.timeTrial(100, 100));
            StdOut.printf("N = 1000, M = 100 TotalTime : %f\n",
                    DoublingRatio.timeTrial(1000, 100));
        }
    }
}