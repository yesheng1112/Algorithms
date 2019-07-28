package homework1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex29 {

    public static int rank(int key, int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (key > a[i]) sum++;
            else break;
        }
        return sum;
    }


    public static int count(int key, int[] a) {
        int result = search(key, a);
        int sum = 0;
        if (result == 0) return sum;
        sum++;
        int beg = result-1, end = result+1;
        while (beg > 0 && a[beg] == key) {
            beg--;
            sum++;
        }
        while (end < a.length && a[end] == key){
            end++;
            sum++;
        }
        return sum;
    }

    public static int search(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = {2, 6, 99, 88, 99, 55, 66, 44, 44, 1, 3, 2, 3, 99};
        Arrays.sort(a);
        StdOut.println(Arrays.toString(a));
        StdOut.println(rank(44, a));
        StdOut.println(count(99, a));
    }
}
