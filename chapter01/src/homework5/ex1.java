package homework5;

import edu.princeton.cs.algs4.StdOut;

public class ex1 {

    //该类和书上的一样
    static class UF {
        private int accessTotalTimes;
        private int[] id = new int[10];

        {
            for (int i = 0; i < id.length; i++)
                id[i] = i;
        }

        int find(int p) {
            return id[p];
        }

        void union(int p, int q) {
            //数组的访问次数
            int accessTimes = 0;
            int pID = find(p);
            int qID = find(q);
            //查找两次
            accessTimes += 2;
            if (pID != qID) {
                for (int i = 0; i < id.length; i++) {
                    //取出一次
                    accessTimes++;
                    if (id[i] == pID) {
                        id[i] = qID;
                        //写入一次
                        accessTimes++;
                    }
                }
                //访问的总次数
                accessTotalTimes += accessTimes;
                StdOut.printf("连接 %d %d 访问数组 : %d 次  总共访问数组 : %d 次\n",
                        p, q, accessTimes, accessTotalTimes);
                StdOut.println(this);
            } else {
                accessTotalTimes += accessTimes;
                StdOut.printf("%d %d 已连通 访问数组 : %d 次  总共访问数组 : %d 次\n",
                        p, q, accessTimes, accessTotalTimes);
                StdOut.println(this);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("-----------------------------\n");
            sb.append("索引:\t");
            int i = 0;
            //打印相应的索引
            while (i < id.length) sb.append(" ").append(i++);
            sb.append("\n\t\t");
            i = 0;
            //打印相应的数组内容
            while (i < id.length) sb.append(" ").append(id[i++]);
            sb.append("\n---------------------------\n");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        UF uf = new UF();
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
