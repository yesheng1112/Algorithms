package homework1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ex22 {
    /**
     * 递归查找关键词的索引
     *
     * @param key 查找的关键字
     * @param arr 被查找的数组
     * @param low 查找的起始位置
     * @param high 查找的最终位置
     * @return 中间值
     */
    public static int rank(int key, int[] arr, int low, int high, int depth) {
        //先打印缩进
        printCallInfo(low, high, depth);
        if (low > high)
            return -1;
        //右移一位除以二 求出中间值
        int mid = low + ((high - low) >> 1);
        //在前半部分
        if (key < arr[mid])
            return rank(key, arr, low, mid - 1, depth + 1);
        //在后半部分
        else if (key > arr[mid])
            return rank(key, arr, mid + 1, high, depth + 1);
        else
            return mid;
    }

    /**
     * 二分查找 ： 递归描述
     *
     * @param key   要查找的关键字
     * @param arr   传递进来是数组
     * @param depth 查找的深度
     * @return 递归调用二分查找
     */
    public static int binarySearch(int key, int[] arr, int depth) {
        return rank(key, arr, 0, arr.length - 1, depth);
    }

    /**
     * 打印缩进
     *
     * @param indents 缩进数
     */
    private static void printIndent(final int indents) {
        for (int i = 0; i < indents; ++i)
            StdOut.print("----------");
    }

    /**
     * 打印调用信息
     *
     * @param low   查找的起始位置
     * @param high  查找的最终的位置
     * @param depth 查找的深度
     */
    private static void printCallInfo(int low, int high, int depth) {
        StdOut.print(depth + "\t");
        printIndent(depth);
        StdOut.println(low + "\t" + high);
    }

    public static void main(String[] args) {
        int N = 1024;
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i)
            //生成0到N乘50的随机数，并赋值给数组
            arr[i] = StdRandom.uniform(N * 50);
        //排序利用原生的工具类进行排序
        Arrays.sort(arr);
        // 输出随机数组
        StdOut.print("seq = ");
        for (int i = 0; i < N; ++i)
            StdOut.print(arr[i] + "\t");
        // 从随机数组中随机抽取一个元素作为关键字
        int key = arr[StdRandom.uniform(N)];//生成随机数0到N之间的数，所以肯定能肯定能在数组中找到一个值
        StdOut.println("\nkey = " + key);
        StdOut.println("---------------------------------------------------------------------------------------");
        StdOut.print(binarySearch(key, arr, 0));
    }
}
