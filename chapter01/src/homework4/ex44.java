package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public class ex44 {
    static int totalBeforeDuplicationAppear(int N) {
        Set<Integer> set = new HashSet<>();
        int i;
        while (true) {
            i = StdRandom.uniform(N);
            if (set.contains(i))
                return set.size();
            else
                set.add(i);
        }
    }
    public static void main(String[] args) {
        int N = 10000, loop = 1000;
        int average = 0;
        for (int i = 0; i < loop; i++)
            average += totalBeforeDuplicationAppear(N);
        StdOut.printf("理论值 : %f  实验值 : %f\n",
                Math.sqrt(Math.PI * N / 2), average * 1.0 / loop);
    }
}
