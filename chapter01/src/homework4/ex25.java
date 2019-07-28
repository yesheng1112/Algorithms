package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex25 {

    /*
     * 找出丢鸡蛋会摔碎的起始楼层
     *
     * 条件 ： 2 个鸡蛋，N 层楼，最低摔碎楼层为 F
     *
     * 先计算出 sqrt(N)，然后由 1 * sqrt(N), 2 * sqrt(N) ... k * sqrt(N) 逐层试探，最坏是sqrt(N)次
     * 当第一个鸡蛋被摔碎时，k <= sqrt(N)，接下来我们 以从上一次鸡蛋没摔碎的楼层为查找下届，鸡蛋摔碎的楼层为查找上届 sqrt(N)次
     * 然后逐层试探，那么此说最多也就是 sqrt(N) 次，因此合计扔了 2 * sqrt(N) 次
     *
     */
    static class ThrowingEggFromABuilding {
        private static int throwTimes; // 记录扔鸡蛋次数
        private static int brokenEggs; // 记录摔碎的鸡蛋数目

        private static int searchStorey_2SqrtN(boolean[] building) {
            throwTimes = 0;
            brokenEggs = 0;
            int N = building.length;
            int root = (int) Math.sqrt(N), k = root, i = 0;

            // 如果在平方根位置处楼层丢就已经摔碎了, 说明起始楼层一定在 [0, root] 中
            if (building[root]) {
                throwTimes++;
                brokenEggs++;
                //找到第一个不碎的楼层
                for (int j = 0; j <= root; j++) {
                    throwTimes++;
                    if (building[j]) {
                        brokenEggs++;
                        return j;
                    }
                }
            }
            // 否则开始用数列 k * sqrt(N) (k <= sqrt(N)) 逐层楼试探
            // 根号N 2*根号N 3*根号N...
            while (k < N && !building[k - 1]) {
                throwTimes++;
                k = (++i) * root;
            }

            // 如果不是因为数组越界而跳出循环，那么一定是摔碎了
            if (k < N) brokenEggs++;

            // 接着从上一次没摔碎的位置开始丢鸡蛋，直到摔碎
            for (int j = (i - 1) * root - 1; j < Math.min(i * root, N); j++) {
                throwTimes++;
                if (building[j]) {
                    brokenEggs++;
                    return j;
                }
            }
            return -1;
        }
    }

    /*
     * 获取一个随机楼层
     */
    public static boolean[] sourceArr(int N) {
        int leftCount = StdRandom.uniform(1, N);
        boolean[] arr = new boolean[N];
        for (int i = 0; i < leftCount; i++)
            arr[i] = false;
        for (int i = leftCount; i < N; i++)
            arr[i] = true;
        return arr;
    }

    /*
     * 打印楼层
     */
    public static void printArray(boolean[] arr) {
        for (int i = 0; i < arr.length; i++)
            StdOut.printf("%4d ", i + 1);
        StdOut.println();
        for (int i = 0; i < arr.length; i++)
            StdOut.print(arr[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        boolean[] arr = sourceArr(10000);
        int floor_2SqrtN = ThrowingEggFromABuilding.searchStorey_2SqrtN(arr);
        StdOut.printf("方法三 : 丢鸡蛋从楼层 %d 开始会摔碎\n", floor_2SqrtN + 1);
        StdOut.printf("方法三 : 扔了 %d 次，摔碎 %d 次\n", ThrowingEggFromABuilding.throwTimes,
                ThrowingEggFromABuilding.brokenEggs);
    }
}
