package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public class ex45 {
    static int totalAfterCollectedAll(int N) {
        Set<Integer> set = new HashSet<Integer>();
        int count = 0;
        while (!containAll(set, N)) {
            set.add(StdRandom.uniform(N));
            count++;
        }
        return count;
    }

    static boolean containAll(Set<Integer> set, int N) {
        for (int i = 0; i < N; i++)
            if (!set.contains(i)) return false;
        return true;
    }

    public static void main(String[] args) {
        int loops = 100;

        for (int N = 1000; N < 10000; N += 1000) {
            double average = 0;
            for (int i = 0; i < loops; i++)
                average += totalAfterCollectedAll(N);
            average /= loops;
            StdOut.printf("实验值 : %f  N = %d, Hn = %f\n", average, N, average / N);
        }

    }
}
