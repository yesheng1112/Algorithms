package homework5;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex3 {

    static class WeightQuickUnionUF {

        private int[] id = new int[10];
        private int[] sz = new int[10];
        private int accessTotalTimes;
        private int accessTimes;

        {
            for (int i = 0; i < id.length; i++) id[i] = i;
            Arrays.fill(sz, 1);
        }

        public int find(int p) {
            //跟随链接找到根节点
            while (true) {
                accessTimes++;
                if (p != id[p]) {
                    p = id[p];
                    accessTimes++;
                } else break;
            }
            return p;
        }

        @SuppressWarnings("DuplicatedCode")
        private void union(int p, int q) {
            accessTimes = 0;
            int i = find(p);
            int j = find(q);
            if (i == j) {
                accessTotalTimes += accessTimes;
                StdOut.printf("连接 %d %d 访问数组 : %d 次  总共访问数组 : %d 次\n",
                        p, q, accessTimes, accessTotalTimes);
                StdOut.println(this);
                return;
            }
            accessTimes += 2;
            //将小树的根节点链接到大树的根节点
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
            accessTimes += 4;
            accessTotalTimes += accessTimes;
            StdOut.printf("连接 %d %d 访问数组 : %d 次  总共访问数组 : %d 次\n",
                    p, q, accessTimes, accessTotalTimes);
            StdOut.println(this);
        }

        @SuppressWarnings("DuplicatedCode")
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
        WeightQuickUnionUF uf = new WeightQuickUnionUF();
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
