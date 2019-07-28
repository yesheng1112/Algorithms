package homework2;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class ex1 {
    /*
     * 两点间最近距离，用红线描出
     */
    public static void nearstDistance(int N) {
        //设置x轴
        StdDraw.setXscale(0, 100);
        //设置y轴
        StdDraw.setYscale(0, 100);
        //设置笔的半径
        StdDraw.setPenRadius(.001);
        //生成指定N的二维的点
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            //生成随机的点
            points[i] = new Point2D(StdRandom.uniform(0, 100.0),
                    StdRandom.uniform(0, 100.0));
            //并画到指定的位置
            points[i].draw();
        }

        double distance = Double.MAX_VALUE;
        int index1 = 0, index2 = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (i != j) {
                    //计算两点之间的距离，利用的是勾股定理
                    double dis = points[i].distanceTo(points[j]);
                    if (dis < distance && dis != 0) {
                        distance = dis;
                        //记录两个点的下标
                        index1 = i;
                        index2 = j;
                    }
                }
        StdOut.println("两点之间最近距离为 : " + distance);
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(Color.red);
        //取出两个点进行连线绘图
        StdDraw.line(points[index1].x(), points[index1].y(),
                points[index2].x(), points[index2].y());
    }

    public static void main(String[] args) {
        nearstDistance(100);
    }
}
