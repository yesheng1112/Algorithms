package homework3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ex5 {

    //除二取余，倒叙排列，就是二进制
    public static void printBinary(int N) {
        Stack<Integer> stack = new Stack<>();
        while (N > 0){
            stack.push(N % 2);
            N /= 2;
        }
        for (Integer d : stack) StdOut.print(d);
        StdOut.println();
    }

    public static void main(String[] args) {
        printBinary(50);
    }
}
