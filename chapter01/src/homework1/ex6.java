package homework1;

import edu.princeton.cs.algs4.StdOut;

public class ex6 {

    public static void main(String[] args) {
        int f = 0;
        int g = 1;
        //前两个数相加作为第三个数
        for (int i = 0; i <= 15; i++) {
            StdOut.print(f);
            f = f + g;
            g = f - g;
        }
    }
}
