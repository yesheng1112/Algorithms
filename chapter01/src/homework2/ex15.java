package homework2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ex15 {
    public static int[] readInts(String name) {
        In in = new In(name);
        String input = in.readAll();
        String words[] = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < words.length; i++)
            ints[i] = Integer.parseInt(words[i]);
        return ints;
    }

    public static void main(String[] args) {
        for(int i : readInts("C:\\Users\\YS\\Desktop\\algs4-data\\algs4-data\\largeW.txt")) {
            StdOut.println(i);
        }
    }
}
