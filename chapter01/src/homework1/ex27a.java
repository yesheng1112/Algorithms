package homework1;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;

public class ex27a {

    public static void main(String[] args) {
        Counter c = new Counter("calls");
        double b = binomial(10, 5, 0.5,c);
        StdOut.println(b);
        StdOut.println(c);
    }

    public static double binomial(int N, int k, double p, Counter counter) {
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        counter.increment();
        return (1.0 - p) * binomial(N - 1, k, p, counter) + p * binomial(N - 1, k - 1, p, counter);
    }
}
