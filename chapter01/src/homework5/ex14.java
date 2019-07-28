package homework5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ex14 {

    //利用加权quick-union方法，只不过记录的是树的高度
    static class DepthWeightedQuickUnion {
        private int[] id;
        private int[] depth;

        public DepthWeightedQuickUnion(int N) {
            id = new int[N];
            depth = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }

        public int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                StdOut.printf("%d %d 已连通\n", p, q);
                return;
            }
            //小的树挂到大的数上，树的深度不会变大
            if (depth[pRoot] < depth[qRoot]) id[pRoot] = qRoot;
            else if (depth[pRoot] > depth[qRoot]) id[qRoot] = pRoot;
            //只有当两个树的深度一样时候，才会改变树的深度
            else {
                id[pRoot] = qRoot;
                update(pRoot);
            }
            StdOut.printf("连接 %d %d\n", p, q);
            StdOut.println(this);
        }

        //根节点是自己指向自己，所以查找的节点不是当前的节点就树的高度加1
        private void update(int p) {
            while (id[p] != p) {
                depth[id[p]]++;
                p = id[p];
            }
        }

        private int maxTreeDepth() {
            int maxDepth = 0;
            for (int value : depth) {
                if (value > maxDepth) maxDepth = value;
            }
            return maxDepth;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------------\n");
            sb.append("索引\t");
            int i = 0;
            while (i < id.length) sb.append(String.format("%3d", i++));
            sb.append("\n\t");
            i = 0;
            while (i < id.length) sb.append(String.format("%3d", id[i++]));
            sb.append("\n树高\t");
            i = 0;
            while (i < id.length) sb.append(String.format("%3d", depth[i++]));
            sb.append("\n \t最大树高 ").append(maxTreeDepth());
            sb.append("\n--------------------------\n");
            return sb.toString();
        }
    }

    public static void theWorstCondition() {
        int N = 20;
        DepthWeightedQuickUnion dcqu = new DepthWeightedQuickUnion(N);
        dcqu.union(1, 2);
        dcqu.union(3, 4);
        dcqu.union(2, 4); // 构造高度2

        dcqu.union(5, 6);
        dcqu.union(7, 8);
        dcqu.union(6, 8); // 构造高度2

        dcqu.union(4, 8); // 构造高度3

        dcqu.union(10, 11);
        dcqu.union(12, 13);
        dcqu.union(11, 13); // 构造高度2

        dcqu.union(14, 15);
        dcqu.union(16, 17);
        dcqu.union(15, 17); // 构造高度2

        dcqu.union(13, 17); // 构造高度3

        dcqu.union(8, 17); // 构造高度4

        StdOut.printf("树高为 : %d\n", dcqu.maxTreeDepth());
    }

    public static void main(String[] args) {
        //int N = 20;
        //ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(N);
        Stopwatch timer = new Stopwatch();
        //DepthWeightedQuickUnion dcqu = new DepthWeightedQuickUnion(N);
       /* for (int i = 0; i < N; i++) {
            int[] pair = gen.nextPair();
            dcqu.union(pair[0], pair[1]);
        }*/
        theWorstCondition();
        StdOut.printf("执行完毕，耗时为 %.3f 秒\n", timer.elapsedTime());
        //StdOut.printf("树高为 : %d\n", dcqu.maxTreeDepth());
    }
}
