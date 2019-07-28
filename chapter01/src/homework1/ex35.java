package homework1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex35 {

    public static void main(String[] args) {
        int N = 100000000;
        double[] count = new double[13];
        for (int i = 0; i < N; i++) {
            int se1 = StdRandom.uniform(1, 7);
            int se2 = StdRandom.uniform(1, 7);
            count[se1 + se2]++;
        }
        double[] result = new double[13];
        for (int i = 0; i < result.length; i++) {
            result[i] = count[i] / N;
        }
        double[] result2 = dist();
        for(int i = 1; i < result.length; i++)
            StdOut.printf("实验 : %.3f  精确 : %.3f\n", result[i], result2[i]);
    }

    public static double[] dist() {
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i + j] += 1.0;
            }
        }
        for (int k = 2; k <= 2 * SIDES; k++) {
            dist[k] /= 36.0;
        }
        return dist;
    }
}
