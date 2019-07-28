package homework5;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex4 {

    static class WeightQuickUnionUF {
        private int[] id;
        private int[] sz;
        private int accessTotalTimes;
        private int accessTimes;

        @SuppressWarnings("WeakerAccess")
        public WeightQuickUnionUF(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < id.length; i++) id[i] = i;
            Arrays.fill(sz, 1);
        }

        public int find(int p) {
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
            } else {
                accessTimes += 2;
                if (sz[i] < sz[j]) {
                    id[i] = j;
                    sz[j] += sz[i];
                } else {
                    id[j] = i;
                    sz[i] += sz[j];
                }
                accessTimes += 4;
                accessTotalTimes += accessTimes;
                StdOut.printf("连接 %d %d 访问数组Id : %d 次  总共访问数组Id : %d 次\n",
                        p, q, accessTimes, accessTotalTimes);
                StdOut.println(this);
            }
        }

        @SuppressWarnings("DuplicatedCode")
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("----------------------------\n");
            sb.append("索引:\t");
            int i = 0;
            while (i < id.length) sb.append(" ").append(i++);
            sb.append("\nid:\t\t");
            i = 0;
            while (i < id.length) sb.append(" ").append(id[i++]);
            sb.append("\nsz:\t\t");
            i = 0;
            while (i < id.length) sb.append(" ").append(sz[i++]);
            sb.append("\n---------------------------\n");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        WeightQuickUnionUF uf = new WeightQuickUnionUF(10);
        StdOut.println("对照输入");
        uf.union(4,3);
        uf.union(3,8);
        uf.union(6,5);
        uf.union(9,4);
        uf.union(2,1);
        uf.union(8,9);
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        uf.union(1,0);
        uf.union(6,7);

        WeightQuickUnionUF uf1 = new WeightQuickUnionUF(8);
        StdOut.println("最坏输入");
        uf1.union(0,1);
        uf1.union(2,3);
        uf1.union(4,5);
        uf1.union(6,7);
        uf1.union(0,2);
        uf1.union(4,6);
        uf1.union(0,4);
    }
}
