package homework5;

import edu.princeton.cs.algs4.StdOut;

//采用路径压缩的算法
public class ex17 {

    static class CompressedWeightedQuickUnion{

        private int[] id;
        private int[] sz;

        public CompressedWeightedQuickUnion(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        @SuppressWarnings("DuplicatedCode")
        public int find(int p) {
            int root = p;
            while(root!=id[root]) root = id[root];
            while (id[p] != root) {
                int parent = id[p];
                id[p] = root;
                p = parent;
            }
            return root;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (sz[pRoot] < sz[qRoot]){
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }else {
                id[qRoot] = pRoot;
                sz[pRoot] = sz[qRoot];
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        //连接起来只有一个根节点，所以count=1的时候就是全部连接
        public boolean allConnected() {
            int count = 0;
            for (int i = 0; i < id.length; i++) {
                if (id[i] == i) count++;
            }
            return count == 1;
        }

        public static int count(int N) {
            int count = 0;
            ArrayGenerator.RandomPair pair = new ArrayGenerator.RandomPair(N);
            CompressedWeightedQuickUnion quickUnion = new CompressedWeightedQuickUnion(N);
            while (!quickUnion.allConnected()){
                count++;
                int[] par = pair.nextPair();
                if (quickUnion.connected(par[0],par[1])) continue;
                quickUnion.union(par[0],par[1]);
            }
            return count;
        }
    }
    public static void main(String[] args) {
        int N = 1000;
        StdOut.printf("使 %d 个触点全部连通，共生成 %d 对随机连接\n",
                N, CompressedWeightedQuickUnion.count(N));
    }
}
