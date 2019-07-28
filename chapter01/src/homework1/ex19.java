package homework1;

import edu.princeton.cs.algs4.StdOut;

public class ex19 {

    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    public static long [] F1(int N){
        long[] a = new long[N];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i < N; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a;
    }

    public static void main(String[] args) {
        long[] a;
        a = F1(100);
        for (int N = 0; N < 100; N++) {
            StdOut.println(N + " " + a[N]);
        }
    }
}
