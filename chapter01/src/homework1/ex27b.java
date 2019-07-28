package homework1;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;

public class ex27b {

    public static double binomial(int n, int k, double p, Counter c) {
        double[][] v = new double[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= k; j++)
                v[i][j] = -1;

        return binomial(v, n, k, p, c);
    }

    public static double binomial(double[][] v, int n, int k, double p, Counter c) {
        if (n == 0 && k == 0) return 1.0;
        if (n < 0 || k < 0) return 0.0;

        if (v[n][k] == -1) {
            c.increment();
            v[n][k] = (1.0 - p) * binomial(v, n - 1, k, p, c) + p * binomial(v, n - 1, k - 1, p, c);
        }

        return v[n][k];
    }

    public static void main(String[] args) {

        Counter c = new Counter("calls");

        double b = binomial(2, 1, 0.5, c);

        StdOut.println(b);
        StdOut.println(c);
    }
}
