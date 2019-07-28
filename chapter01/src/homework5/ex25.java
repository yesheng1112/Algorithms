package homework5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ex25 {

    interface UF {
        int find(int p);

        boolean connected(int p, int q);

        boolean allCoonected();

        void union(int p, int q);

        void reset();
    }

    static void doubleRationTest(int T, int begN, Class<?> type) throws Exception {
        double prev = 0, cur;
        int pairCount;
        for (int i = begN, j = 0; j < T; i += i, j++) {
            int tmpI = (int) Math.sqrt(i);
            UF uf = (UF) type.getDeclaredConstructor(int.class).newInstance(tmpI * tmpI);
            ex18.RandomBag<ex18.Connection> rb = ex18.randomGridGenerator(tmpI);
            cur = 0;
            pairCount = 0;
            int testCount = 3;
            for (int k = 0; k < testCount; k++) {
                Stopwatch timer = new Stopwatch();
                for (ex18.Connection c : rb) {
                    if (uf.allCoonected()) break;
                    pairCount++;
                    if (uf.connected(c.p, c.q)) continue;
                    uf.union(c.p, c.q);
                }
                cur += timer.elapsedTime();
            }
            cur /= (testCount * 1.0);
            pairCount /= (testCount * 1.0);
            StdOut.printf("规模 : %d "
                    + "连接数 : %d "
                    + "耗时 : %f "
                    + "倍率 : %f\n", tmpI * tmpI, pairCount, cur, cur / prev);
            prev = cur;
        }
    }

    // quick-find
    static class QF implements UF {

        private int[] id;
        private int count;

        QF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
            count = N;
        }

        @Override
        public int find(int p) {
            return id[p];
        }

        @Override
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        @Override
        public boolean allCoonected() {
            return count == 1;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pRoot) id[i] = qRoot;
            }
            count--;
        }

        @Override
        public void reset() {
            for (int i = 0; i < id.length; i++)
                id[i] = i;
            count = id.length;
        }
    }

    // quick-union
    static class QU implements UF {

        private int[] id;
        private int count;

        QU(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
            count = N;
        }

        @Override
        public int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;
        }

        @Override
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        @Override
        public boolean allCoonected() {
            return count == 1;
        }

        @Override
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            id[pRoot] = qRoot;
            count--;
        }

        @Override
        public void reset() {
            for (int i = 0; i < id.length; i++)
                id[i] = i;
            count = id.length;
        }
    }

    //weighted-quick-union
    static class WQU implements UF {

        private int[] id;
        private int[] sz;
        private int count;

        WQU(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
            count = N;

        }

        @Override
        public int find(int p) {
            while (p != id[p])
                p = id[p];
            return p;
        }

        @Override
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        @Override
        public boolean allCoonected() {
            return count == 1;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (sz[pRoot] < sz[qRoot]) {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }else {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }
            count--;
        }

        @Override
        public void reset() {
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                sz[i] = 1;
            }
            count = id.length;
        }
    }

    public static void main(String[] args) throws Exception{
        //doubleRationTest(14, 100, QF.class);
        //doubleRationTest(14, 100, QU.class);
        doubleRationTest(14, 100, WQU.class);
    }
}
