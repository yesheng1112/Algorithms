package homework5;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class ex12 {
    //路径压缩的QuickUnion算法，路径上的所有的点全部连接到根节点上
    static class CompressedQuickUnion {

        private int[] id;

        public CompressedQuickUnion(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }

        public int find(int p) {
            int root = p;
            //先记录根节点的值
            while (root != id[root]) root = id[root];
            //把所有的节点连接到根节点
            while (root != id[p]){
                int parent = id[p];
                id[p] = root;
                p = parent;
            }
            return root;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot){
                StdOut.printf("%d %d 已连通\n", p, q);
                return;
            }
            id[pRoot] = qRoot;
            StdOut.printf("连接 %d %d\n", p, q);
            StdOut.println(this);
        }

        //获取树的最大深度
        public int maxTreeDepth() {
            int depth = 0;
            for (int i = 0; i < id.length; i++) {
                int tmp = 0;
                int p = i;
                //根节点指向本身,所以循环下去，就一定是根节点，最后求出树的高度
                while (p != id[p]){
                    tmp++;
                    p = id[p];
                }
                if (tmp > depth) depth = tmp;
            }
            return depth;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------\n");
            sb.append("索引:\t");
            int i = 0;
            while (i < id.length) sb.append(" ").append(i++);
            sb.append("\n\t\t");
            i = 0;
            while (i<id.length) sb.append(" ").append(id[i++]);
            sb.append("\n\t最大树高 ").append(maxTreeDepth());
            sb.append("\n-----------------------\n");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        int N = 12;
        CompressedQuickUnion cqu = new CompressedQuickUnion(N);
        ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(N);
        ArrayList<Object> container = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int[] pair = gen.nextPair();
            cqu.union(pair[0], pair[1]);
            container.add(pair);
        }
        StdOut.println();
        StdOut.printf("以下序列产生的树高为 : %d\n", cqu.maxTreeDepth());
        for (Object a : container) {
            int[] arr = (int[])a;
            StdOut.printf("%-2d %2d\n",arr[0], arr[1]);
        }
    }
}
