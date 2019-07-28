package homework1;

import edu.princeton.cs.algs4.*;

public class ex36 {
    /*
     * 该打乱算法存在打乱后和原数组相等的可能
     */
    public static void shuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(N);
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /*
     * 创建大小为 M 的数组
     */
    public static double[] sourceArr(int M) {
        // M = 10
        double[] arr = new double[M];
        for (int i = 0; i < M; i++)
            arr[i] = i;
        return arr;
    }

    /*
     * 重置数组为下标序列
     */
    public static void resetArr(double[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = i;
    }

    /*
     * 打印数组
     */
    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                StdOut.printf("%-5d", arr[i][j]);
            }
            StdOut.println();
        }
        StdOut.println("===============");
    }

    /*
     * 打乱测试
     *
     * @param M 准备打乱的数组大小
     * @param N 打乱数组的次数
     */
    public static void ShuffleTest(int M, int N) {
        //M = 10 N = 1000
        double[] source = sourceArr(M);

        int[][] recordTable = new int[M][];
        //M*M 10*10
        for (int i = 0; i < M; i++)
            recordTable[i] = new int[M];
        //执行 N 次打乱并记录每个元素落在每列的次数
        record(recordTable, N, source);

        StdOut.println("N / M = " + N / M);
        printArr(recordTable);
    }

    /**
     * 执行 N 次打乱并记录每个元素落在每列的次数
     *
     * @param recordTable 打印的表格
     * @param shuffleCount 打乱的次数
     * @param source 要打乱的数组
     */
    public static void record(int[][] recordTable, int shuffleCount, double[] source) {
        for (int n = 0; n < shuffleCount; n++) {
            //重置数组下标序列
            resetArr(source);
            //生成随机下标进行交换
            shuffle(source);
            for (int i = 0; i < source.length; i++)
                for (int k = 0; k < source.length; k++)
                    if ((int) source[k] == i)
                        recordTable[i][k]++;
        }
    }

    public static void main(String[] args) {
        //打印出来的结果如果都是1000证明每次的打乱的顺序都不一样
        //表格中数据越接近N/M，证明每次打乱的顺序越不相同
        ShuffleTest(10 /* 数组大小 */, 10000 /* 打乱次数 */);
    }
}