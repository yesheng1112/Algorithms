package homework5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class ex23 {

    static void test(int T, int begN) {
        List<Object> list = new ArrayList<>();
        double qfTime, quTime;
        for (int i = begN, j = 0; j < T; i += i, j++) {
            QF qf = new QF(i);
            QU qu = new QU(i);
            list.clear();
            ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(i);
            while (!qu.allConnected()) {
                int[] pair = gen.nextPair();
                list.add(pair);
                qu.union(pair[0], pair[1]);
            }
            qu.reset();

            //对quick-find计时
            Stopwatch timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[]) a;
                if (!qf.connected(arr[0], arr[1]))
                    qf.union(arr[0], arr[1]);
            }
            qfTime = timer.elapsedTime();

            //对quick-union计时
            timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[]) a;
                if (!qu.connected(arr[0], arr[1]))
                    qu.union(arr[0], arr[1]);
            }
            quTime = timer.elapsedTime();

            StdOut.printf("【规模: %d 连接数 : %d】\t QU 用时 : %f \t QF 用时 : %f, \tQU/QF = %f\n", i, list.size(), quTime, qfTime, quTime / qfTime);
            if (!qf.allConnected() || !qu.allConnected())
                throw new RuntimeException("qf 或 qu 没有全部连通");
        }
    }

    static class QF {

        private int[] id;
        private int count;

        QF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
            count = N;
        }

        public int find(int p) {
            return id[p];
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        boolean allConnected() {
            return count == 1;
        }

        @SuppressWarnings("DuplicatedCode")
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            for (int i = 0; i < id.length; i++)
                if (id[i] == pRoot) id[i] = qRoot;
            count--;
        }

        public void reset() {
            for (int i = 0; i < id.length; i++)
                id[i] = i;
            count = id.length;
        }
    }

    static class QU {

        private int[] id;
        private int count;

        QU(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
            count = N;
        }

        public int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        @SuppressWarnings("BooleanMethodIsAlwaysInverted")
        boolean allConnected() {
            return count == 1;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            id[pRoot] = qRoot;
            count--;
        }

        void reset() {
            for (int i = 0; i < id.length; i++)
                id[i] = i;
            count = id.length;
        }
    }

    public static void main(String[] args) {
        test(10,100);
    }
}
