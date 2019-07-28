package homework1;

import edu.princeton.cs.algs4.*;

import java.util.*;

public class ex32 {

    /**
     * @param arr 模拟输入一系列的值
     * @param N   分成多少段
     * @param l   起始的位置
     * @param r   终点的位置
     */
    public static void draw(double arr[], int N, double l, double r) {
        // 设置 x 轴的边界
        StdDraw.setXscale(0, r);

        // 设置 y 轴的边界
        StdDraw.setYscale(0, arr.length);

        // 设置笔触的大小
        StdDraw.setPenRadius(.001);

        // 分配一个 N 段的数组
        double[] counter = new double[N];
        double[] sections = new double[N + 1];

        //输入的两个的数进行分段
        double average = (r - l) / N;

        //把分段的结果保存的分段数组中
        for (int i = 0; i < N + 1; i++)
            sections[i] = l + average * i;

        // 在给自区间的桶中计数
        for (int i = 0; i < arr.length; i++) {
            double elem = arr[i];
            for (int j = 1; j < N + 1; j++)
                if (elem > sections[j - 1] && elem <= sections[j])
                    counter[j - 1]++;
        }
        StdOut.println(Arrays.toString(counter));

        // 绘制图像
        for (int i = 0; i < counter.length; i++) {
            double x = (l + i * average) + (average / 2);
            double y = counter[i] / 2;
            double rw = average / 2;
            double rh = counter[i] / 2;
            //x表示矩形中点的x坐标
            //y表示矩形中点的y坐标
            //rw表示矩形宽度的一半
            //rh表示矩形高度的一半
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }


    /**
     * @param l 生成随机数的起始位置
     * @param r 生成随机数的最大位置
     * @return 生成的数组
     */
    public static double[] randomArr(double l, double r) {
        int N = StdRandom.uniform(100);
        double[] arr = new double[N];
        for (int i = 0; i < N; i++)
            arr[i] = StdRandom.uniform(l, r);
        return arr;
    }

    public static void main(String[] args) {
        double[] doubles = randomArr(1, 3);
        StdOut.println(Arrays.toString(doubles));
        StdOut.println(doubles.length);
        draw(doubles, 10, 1, 3);
    }

}