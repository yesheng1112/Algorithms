package homework5;

import edu.princeton.cs.algs4.StdOut;

public class ex2 {
    static class QuickUnionUF {
        private int[] id = new int[10];
        private int accessTotalTimes;
        private int accessTimes;

        {
            for (int i = 0; i < 10; i++)
                id[i] = i;
        }

        public int find(int p) {
            while (true) {
                accessTimes++;
                if (p != id[p]) {
                    accessTimes++;
                    p = id[p];
                } else
                    break;
            }
            return p;
        }

        public void union(int p, int q) {
            accessTimes = 0;
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                //访问的总次数
                accessTotalTimes += accessTimes;
                StdOut.printf("连接 %d %d 访问数组 : %d 次  总共访问数组 : %d 次\n",
                        p, q, accessTimes, accessTotalTimes);
                StdOut.println(this);
                return;
            }
            accessTimes++;
            id[pRoot] = qRoot;
            //访问的总次数
            accessTotalTimes += accessTimes;
            StdOut.printf("连接 %d %d 访问数组 : %d 次  总共访问数组 : %d 次\n",
                    p, q, accessTimes, accessTotalTimes);
            StdOut.println(this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("----------------------------\n");
            sb.append("索引:\t");
            int i = 0;
            while (i < id.length) sb.append(" ").append(i++);
            sb.append("\n\t\t");
            i = 0;
            while (i < id.length) sb.append(" ").append(id[i++]);
            sb.append("\n---------------------------\n");
            return sb.toString();
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF();
        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 8);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);
    }
}
