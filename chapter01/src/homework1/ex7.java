package homework1;

import edu.princeton.cs.algs4.StdOut;

public class ex7 {

    public static void main(String[] args) {
        double t = 9.0;
        //求绝对值
        while (Math.abs(t - 9.0 / t) > .001)
            t = (9.0 / t + t) / 2.0;
        StdOut.printf("%.5f\n", t);

        int sum1 = 0;
        // 0 0
        // 1 0+1
        // 2 0+1+2
        // 3 0+1+2+3
        //...
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum1++;
            }
        }
        StdOut.println(sum1);

        int sum2 = 0;
        for (int i = 1; i < 1000; i*=2) {
            for (int j = 0; j < 1000; j++) {
                sum2++;
            }
        }
        StdOut.println(sum2);
    }
}
