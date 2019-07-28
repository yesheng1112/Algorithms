package homework5;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static homework5.ex18.*;

public class ex19 {
    //深度加权的UF
    static class DepthWeightedQuickUnion {
        private int[] id;
        private int[] depth;

        DepthWeightedQuickUnion(int N) {
            id = new int[N];
            depth = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }

        int[] id() {
            return id;
        }

        boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }

        void update(int p) {
            while (p != id[p]) {
                depth[id[p]]++;
                p = id[p];
            }
        }

        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (depth[pRoot] > depth[qRoot]) id[qRoot] = pRoot;
            else if (depth[pRoot] < depth[qRoot]) id[pRoot] = qRoot;
            else {
                id[pRoot] = qRoot;
                update(pRoot);
            }
        }
    }

    public static void draw(int N) throws Exception {
        //正方形所以开个平方
        N = (int) Math.sqrt(N);
        //从负二开始为了有一个边界
        StdDraw.setXscale(-2, N);
        StdDraw.setYscale(-2, N);
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(Color.BLACK);
        //画图上的点
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++)
                StdDraw.point(j, i);
        }
        //生成相应的链接点
        DepthWeightedQuickUnion dwqu = new DepthWeightedQuickUnion(N * N);
        //生成相应的连通点
        RandomBag<Connection> rb = ex18.randomGridGenerator(N);
        for (Connection connection : rb) {
            if (dwqu.connected(connection.p, connection.q)) {
                StdOut.printf("{%d %d} 已连通\n", connection.p, connection.q);
                continue;
            }
            dwqu.union(connection.p, connection.q);
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(0.003);
            //p
            int x0 = connection.p / N;
            int y0 = connection.p % N;

            //q
            int x1 = connection.q / N;
            int y1 = connection.q % N;
            StdDraw.line(x0, y0, x1, y1);
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    public static void main(String[] args) throws Exception {
        draw(810);
    }
}