package homework5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ex13 {

    static class CompressedWeightQuickUnion {
        private int[] id;
        private int[] sz;

        public CompressedWeightQuickUnion(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        @SuppressWarnings("DuplicatedCode")
        //原来的根节点下面的节点还是挂在根节点上的，所以节点的数目不会变，故不需要改变sz的值
        public int find(int p) {
            int root = p;
            while (root != id[root]) root = id[root];
            while (id[p] != root) {
                int parent = id[p];
                id[p] = root;
                p = parent;
            }
            return root;
        }

        @SuppressWarnings("DuplicatedCode")
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                //StdOut.printf("%d %d已连通\n", p, q);
                return;
            }
            if (sz[pRoot] < sz[qRoot]) {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            } else {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }
            //StdOut.printf("连接 %d %d\n", p, q);
            //StdOut.println(this);
        }

        @SuppressWarnings("DuplicatedCode")
        int maxTreeDepth() {
            int depth = 0;
            for (int i = 0; i < id.length; i++) {
                int tmp = 0;
                int p = i;
                while (p != id[p]) {
                    tmp++;
                    p = id[p];
                }
                if (tmp > depth) depth = tmp;
            }
            return depth;
        }

        @SuppressWarnings("DuplicatedCode")
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------\n");
            sb.append("索引:\t");
            int i = 0;
            while (i < id.length) sb.append(" ").append(i++);
            sb.append("\n\t\t");
            i = 0;
            while (i < id.length) sb.append(" ").append(id[i++]);
            sb.append("\n\t最大树高 ").append(maxTreeDepth());
            sb.append("\n-----------------------\n");
            return sb.toString();
        }
    }

    /*
     * 路径压缩的加权 quick-union 方法几乎是解决大规模连通性问题的最优方案
     *
     * 以下对 10^8 个触点，进行 10^9 对连接的处理
     * 多次运行，可以得出最后形成的树高几乎不会超过 5，基本上为 1
     *
     * 对于触点数目固定的 M 次连接操作，连接随机的两个触点，形成的树高和 M 的关系应该是类似于正态分布的函数
     * 当 M 次数很小时，随机的两个触点深度几乎为0，因此新树的合并很少，基本上是两个深度为0触点的连接，形成高度为1的新子树
     * 当 M 次数接近触点数目时，就很可能让小子树连接到大子树上，同时增加高度
     * 当 M 次数很大时，由于每次 find 操作都有可能将一条深路径上的各个触点变得直接与根结点相连，即便是
     * 检查两个已经连接的触点，都有可能造成树高的缩减，因此 M 次数越大，就会使树越矮
     * 我们通过修改触点个数和连接对数不断运行下列程序，会发现
     * 当 M >> N 时，树高几乎都是 1
     * 当 M ~ N 时，树高几乎能达到但不会超过 5
     * 当 M << N 时，树高几乎都是 1
     *
     */
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        int N = 100000000;
        ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(N);
        CompressedWeightQuickUnion cqu = new CompressedWeightQuickUnion(N);
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < N; i++) {
            int[] arr = gen.nextPair();
            cqu.union(arr[0], arr[1]);
        }
        StdOut.printf("执行完毕，耗时为 %.3f 秒\n", timer.elapsedTime());
        StdOut.printf("树高为 : %d\n", cqu.maxTreeDepth());
    }
}
