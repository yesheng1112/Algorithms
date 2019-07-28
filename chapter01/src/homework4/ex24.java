package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex24 {
    /*
     * 查找丢鸡蛋会摔碎的起始楼层
     *
     * 思路 :
     *
     * 条件 ：N 层楼，无数个鸡蛋, 最低摔碎楼层为 F
     *
     * 对于摔碎 lgN 个鸡蛋 的思路，首先在 (lo + hi) / 2 层扔，如果摔碎了，把查找上届缩小至 mid
     * 如果没摔碎，把查找下届增加至 mid + 1, 假如 mid 率先命中最低摔碎楼层，那么接下来所有分支都会走 lo = mid + 1
     * 最后循环在 lo == hi 时跳出
     * 假如 mid + 1 率先命中最低摔碎楼层，那么接下来所有分支都会走 hi = mid, 最后循环在 lo == hi 时跳出
     *
     *
     * 对于 摔碎2lgF个鸡蛋 的思路，首先我们挑选 2^i 层逐层试探，这样第一次摔碎鸡蛋时摔碎数量为 ~lgF
     * 接下来我们在 以上一次没摔碎楼层为查找下届和以第一次摔碎楼层为查找上届间二分查找，这样摔碎的鸡蛋数量为 ~lgF
     * 合计 ~2lgF
     *
     */
    static class ThrowingEggsFromABuilding {
        private static int brokenEggs; // 记录摔碎的鸡蛋数
        private static int throwTimes; // 记录丢鸡蛋次数

        /**
         * 查找摔碎鸡蛋的楼层（二分查找）
         *
         * @param building 楼层数组
         * @return 查找的楼层
         */
        private static int searchStorey_lgN(boolean[] building) {
            brokenEggs = 0; //摔碎鸡蛋数
            throwTimes = 0; //丢鸡蛋的次数
            //总的楼层
            int N = building.length;
            // 如果在顶层丢都不会碎，那就不用找了
            if (!building[N - 1]) return -1;
            int lo = 0, hi = N - 1, mid;
            // lgN
            while (lo < hi) {
                mid = (lo + hi) >> 1;
                throwTimes++;

                // 如果在 mid 层丢碎了，那么把查找上届缩小至 mid
                if (building[mid]) {
                    hi = mid - 1;
                    brokenEggs++;
                }

                // 如果在 mid 层没有碎，那么把查找上届缩小至 mid + 1
                if (!building[mid]) lo = mid + 1;
            }
            // 如果是因为 lo 越过了 hi 而跳出循环，说明没找到
            if (lo > hi) return -1;

            // 否则，lo 和 hi 相遇，此时便是起始楼层
            return hi;
        }

        /**
         * 查找摔碎鸡蛋的楼层
         *
         * @param building 楼层数组
         * @return 摔碎的楼层
         */
        private static int searchStorey_2lgF(boolean[] building) {
            brokenEggs = 0;
            throwTimes = 0;
            int N = building.length;
            int k = 1;

            // 先用序列 2^k 逐层试探 false不会碎， true会碎
            while (k < N && !building[k]) {
                throwTimes++;
                k *= 2;
            }

            //如果不是因为数组越界而跳出循环，说明摔碎了鸡蛋
            if (k < N) brokenEggs++;

            // 选取一个合适的查找上届
            k /= 2;
            //二分查找
            int lo = k, hi = k * 2 >= N ? N - 1 : k * 2, mid;
            while (lo < hi) {
                mid = (lo + hi) >> 1;
                throwTimes++;
                if (building[mid]) {
                    hi = mid-1;
                    brokenEggs++;
                }
                if (!building[mid]) lo = mid + 1;
            }
            if (lo > hi) return -1;
            return hi;
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
        for (boolean b : arr) StdOut.print(b + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        boolean[] arr = sourceArr(1000000);
        int floor_lgN = ThrowingEggsFromABuilding.searchStorey_lgN(arr);
        StdOut.printf("方法一 : 丢鸡蛋从楼层 %d 开始会摔碎\n", floor_lgN + 1);//数组从0开始，楼层从1开始
        StdOut.printf("方法一 : 扔了 %d 次，摔碎 %d 次\n", ThrowingEggsFromABuilding.throwTimes, ThrowingEggsFromABuilding.brokenEggs);

        int floor_lgF = ThrowingEggsFromABuilding.searchStorey_2lgF(arr);
        StdOut.printf("方法二 : 丢鸡蛋从楼层 %d 开始会摔碎\n", floor_lgF + 1);
        StdOut.printf("方法二 : 扔了 %d 次，摔碎 %d 次\n", ThrowingEggsFromABuilding.throwTimes, ThrowingEggsFromABuilding.brokenEggs);
    }
}
