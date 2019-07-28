package homework1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex28 {

    public static int rank(int key, int[] a, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = {11, 25, 63, 98, 12, 11, 11, 11, 12, 1, 35, 98, 98};
        int size = 0;
        Arrays.sort(whitelist);
        size = getSize(whitelist, size);

        int[] newWhitelist = getInts(whitelist, size);

        StdOut.println(size);
        StdOut.println(Arrays.toString(whitelist));
        StdOut.println(Arrays.toString(newWhitelist));

    }

    //将不重复的数组拷贝到新数组中
    private static int[] getInts(int[] whitelist, int size) {
        int index = 0;
        int[] newWhitelist = new int[size];
        for (int i = 0; i < whitelist.length - 2; i++) {
            int lo = i + 1;
            int rank = rank(whitelist[i], whitelist, lo, whitelist.length - 1);
            if (rank == -1) {
                newWhitelist[index] = whitelist[i];
                index++;
            }
        }

        if (whitelist[whitelist.length - 2] == whitelist[whitelist.length - 1]) {
            newWhitelist[index] = whitelist[whitelist.length - 2];
        } else {
            newWhitelist[index] = whitelist[whitelist.length - 2];
            newWhitelist[index + 1] = whitelist[whitelist.length - 1];
        }
        return newWhitelist;
    }

    //获取新的数组的长度
    private static int getSize(int[] whitelist, int size) {
        for (int i = 0; i < whitelist.length - 2; i++) {
            int lo = i + 1;
            int rank = rank(whitelist[i], whitelist, lo, whitelist.length - 1);
            if (rank != -1) {
                size++;
            }
        }
        if (whitelist[whitelist.length - 2] == whitelist[whitelist.length - 1]) {
            size = whitelist.length - size - 1;
        } else {
            size = whitelist.length - size;
        }
        return size;
    }
}
