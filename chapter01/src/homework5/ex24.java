package homework5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class ex24 {

    @SuppressWarnings("DuplicatedCode")
    static void test(int T, int begN) {
        List<Object> list = new ArrayList<>();
        double qfTime, quTime, wquTime, cwquTime;
        for (int i = begN, j = 0; j < T; i += i, j++) {
            QF qf = new QF(i);
            QU qu = new QU(i);
            WQU wqu = new WQU(i);
            CWQU cwqu = new CWQU(i);

            list.clear();

            ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(i);
            while (!wqu.allConnected()) {
                int[] pair = gen.nextPair();
                list.add(pair);
                wqu.union(pair[0], pair[1]);
            }
            wqu.reset();

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

            //对weighted-quick-union计时
            timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[]) a;
                if (!wqu.connected(arr[0], arr[1]))
                    wqu.union(arr[0], arr[1]);
            }
            wquTime = timer.elapsedTime();

            //对compressed-weighted-quick-union计时
            timer = new Stopwatch();
            for (Object a : list) {
                int[] arr = (int[]) a;
                if (!cwqu.connected(arr[0], arr[1]))
                    cwqu.union(arr[0], arr[1]);
            }
            cwquTime = timer.elapsedTime();

            StdOut.printf("【规模: %d 连接数 : %d】\t QU 用时 : %.0f毫秒 \t QF 用时 : %.0f毫秒, \t WQU 用时:%.0f毫秒,\t CWQU 用时:%.0f毫秒\n", i, list.size(), quTime, qfTime, wquTime, cwquTime);
            if (!qf.allConnected() || !qu.allConnected() || !wqu.allConnected() || !cwqu.allConnected())
                throw new RuntimeException("qf 或 qu 或 wqu 或 cwqu 没有全部连通");
        }
    }

    //quick-find
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

    //quick-union
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

    //weighted-quick-union
    static class WQU {
        private int[] id;
        private int[] size;
        private int count;

        WQU(int N) {
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = N;
        }

        public void reset() {
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = id.length;
        }

        public int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;
        }

        boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        boolean allConnected() {
            return count == 1;
        }

        @SuppressWarnings("DuplicatedCode")
        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (size[pRoot] < size[qRoot]) {
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            } else {
                id[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
            count--;
        }
    }

    //compressed-weighted-quick-union
    static class CWQU {
        private int[] id;
        private int[] size;
        private int count;

        CWQU(int N) {
            id = new int[N];
            size = new int[N];
            count = N;
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }

        public void reset() {
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = id.length;
        }

        @SuppressWarnings("DuplicatedCode")
        public int find(int p) {
            int root = p;
            while (root != id[root])
                root = id[root];
            while (id[p] != root) {
                int parent = id[p];
                id[p] = root;
                p = parent;
            }
            return root;
        }

        boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        boolean allConnected() {
            return count == 1;
        }

        @SuppressWarnings("DuplicatedCode")
        void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (size[pRoot] < size[qRoot]) {
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            } else {
                id[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
            count--;
        }
    }

    public static void main(String[] args) {
        test(15, 100);
    }
}
