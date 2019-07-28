package homework1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex23 {
    /**
     * 二分查找 ： 非递归描述
     *
     * @param key 关键字
     * @param arr 数组
     * @return 若找到则返回true，否则返回false
     */
    public static boolean BinaryLookup(int key, int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (key < arr[mid])
                high = mid - 1;
            else if (key > arr[mid])
                low = mid + 1;
            else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // “ + ” --> 打印出标准输入中不在白名单上的值，
        // “ - ” --> 打印出标准输入中在白名单上的值
        char symbol = '+';
        int[] whitelist = {11,22,33,44,55,6677};
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            boolean found = BinaryLookup(key, whitelist);
            if ('+' == symbol && !found)
                StdOut.println(key);
            if ('-' == symbol && found)
                StdOut.println(key);
        }
    }
}
