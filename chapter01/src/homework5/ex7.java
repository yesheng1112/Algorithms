package homework5;

import edu.princeton.cs.algs4.StdOut;

public class ex7 {

    static class QuickFindUF {
        private int[] id;
        private int count;

        public QuickFindUF(int N) {
            count = N;
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }

        public int count() {
            return count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int find(int p) {
            return id[p];
        }

        public void union(int p, int q) {
            int pId = find(p);
            int qId = find(q);

            if (pId == qId) {
                StdOut.printf("%d %d 已连通\n\n", p, q);
                return;
            }

            for (int i = 0; i < id.length; i++)
                if (id[i] == qId) id[i] = qId;
            count--;
            StdOut.println(this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            sb.append("-----------------------------\n");
            sb.append("索引:\t");
            while (i < id.length) sb.append(" ").append(i++);
            sb.append("\n\t");
            i = 0;
            while (i < id.length) sb.append(" ").append(id[i++]);
            sb.append("        连通分量 : ").append(count);
            sb.append("\n-----------------------------\n");
            return sb.toString();
        }
    }

    static class QuickUnionUF {
        private int[] id;
        private int count;

        public QuickUnionUF(int N) {
            count = N;
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }

        public int count() {
            return count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                StdOut.printf("%d %d 已连通\n\n", p, q);
                return;
            }
            id[pRoot] = qRoot;
            count--;
            StdOut.println(this);
        }

        @SuppressWarnings("DuplicatedCode")
        public String toString() {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            sb.append("-----------------------------\n");
            sb.append("索引:\t");
            while (i < id.length) sb.append(" ").append(i++);
            sb.append("\n\t");
            i = 0;
            while (i < id.length) sb.append(" ").append(id[i++]);
            sb.append("        连通分量 : ").append(count);
            sb.append("\n-----------------------------\n");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        StdOut.println("quick-union\n");
        QuickUnionUF qu = new QuickUnionUF(10);
        ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(10);
        for (int i = 0; i < 20; i++) {
            int[] pair = gen.nextPair();
            StdOut.printf("连接 %d %d\n", pair[0], pair[1]);
            qu.union(pair[0], pair[1]);
        }


        StdOut.println("quick-find\n");
        QuickFindUF qf = new QuickFindUF(10);
        for (int i = 0; i < 20; i++) {
            int[] pair = gen.nextPair();
            StdOut.printf("连接 %d %d\n", pair[0], pair[1]);
            qf.union(pair[0], pair[1]);
        }
    }

}
