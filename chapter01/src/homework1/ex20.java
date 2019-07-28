package homework1;

import edu.princeton.cs.algs4.StdOut;

public class ex20 {
    public static long factorial(int M) {
        if(0 == M || 1 == M)
            return 1;
        else
            return M * factorial(M - 1);
    }

    public static double ln(int N) {
        return Math.log(factorial(N));
    }

    public static void main(String[] args) {
        double val = ln(5);
        StdOut.println(val);
    }
}
