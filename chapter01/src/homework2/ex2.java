package homework2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex2 {
    /*
     * 打印相交
     */
    public static void intersectPrint(int N) {
        //创建 N 个间隔
        Interval1D[] intervals = new Interval1D[N];
        for (int i = 0; i < N; i++) {
            double x1 = StdRandom.uniform(0, 100);
            double x2 = StdRandom.uniform(0, 100);
            //从小到大排列
            if (x1 > x2) {
                double tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            //构造函数要求，小的在前，大的在后
            intervals[i] = new Interval1D(x1, x2);
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (i != j) {
                    //判断两个间隔是否相交
                    if (intervals[i].intersects(intervals[j])) {
                        StdOut.println(intervals[i] + " 和 " + intervals[j] + " 相交");
                    }
                }
    }

    public static void main(String[] args) {
        intersectPrint(10);
    }
}
