package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ex21 {
    static class StaticsSETofInts {
        private int[] a;

        public StaticsSETofInts(int[] keys) {
            a = new int[keys.length];
            System.arraycopy(keys, 0, a, 0, keys.length);
            Arrays.sort(a);
        }

        public boolean contains(int key) {
            return rank(key) != -1;
        }

        private int rank(int key) {
            int lo = 0;
            int hi = a.length - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                if (key < a[mid]) hi = mid - 1;
                if (key > a[mid]) lo = mid + 1;
                else return mid;
            }
            return -1;
        }
    }

    public static int[] sourceArr(int N) {
        Set<Integer> set = new HashSet<Integer>();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int a = StdRandom.uniform(0, 100);
            while (set.contains(a))
                a = StdRandom.uniform(0, 100);
            arr[i] = a;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if ((i + 1) % 10 == 0)
                StdOut.printf("%3d\n", arr[i]);
            else
                StdOut.printf("%3d", arr[i]);
        StdOut.println();
    }

    public static void main(String[] args) {
        int[] arr = sourceArr(100);
        printArray(arr);
        StaticsSETofInts ints = new StaticsSETofInts(arr);
        int key = 5;
        if (ints.contains(key))
            StdOut.println("contain " + key);
        else
            StdOut.println("not exist");
    }
}
