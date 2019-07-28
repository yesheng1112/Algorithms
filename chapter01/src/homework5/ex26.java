package homework5;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class ex26 {

    interface UF {
        int find(int p);

        boolean connected(int p, int q);

        boolean allConnected();

        void union(int p, int q);

        void cleanPrevAccess();

        int prevAccess();

        int totalAccess();
    }

    static void amortizedDraw(int N, Class<?> type) throws Exception {
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-N, N * 2);
        StdDraw.setPenRadius(.003);
        UF uf = (UF) type.getDeclaredConstructor(int.class).newInstance(N);
        ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(N);
        int i = 0;
        while (!uf.allConnected()) {
            int[] pair = gen.nextPair();
            i++;
            uf.cleanPrevAccess();
            if (!uf.connected(pair[0], pair[1]))
                uf.union(pair[0], pair[1]);
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.point(i, uf.prevAccess());
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, uf.totalAccess() / i);
        }
    }

    static class QF implements UF {

        private int[] id;
        private int count;
        private int accessTotalTimes;
        private int eachLoopAccessTimes;

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
            eachLoopAccessTimes += 2;
            return find(p) == find(q);
        }

        @Override
        public boolean allConnected() {
            return count == 1;
        }

        @Override
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            eachLoopAccessTimes += 2;
            if (pRoot != qRoot) {
                for (int i = 0; i < id.length; i++) {
                    eachLoopAccessTimes++;
                    if (id[i] == pRoot) {
                        id[i] = qRoot;
                        eachLoopAccessTimes++;
                    }
                }
                count--;
            }
            accessTotalTimes += eachLoopAccessTimes;
        }

        @Override
        public void cleanPrevAccess() {
            eachLoopAccessTimes = 0;
        }

        @Override
        public int prevAccess() {
            return eachLoopAccessTimes;
        }

        @Override
        public int totalAccess() {
            return accessTotalTimes;
        }
    }

    static class QU implements UF {

        private int[] id;
        private int count;
        private int accessTotalTimes;
        private int eachLoopAccessTimes;

        QU(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
            count = N;
        }

        @Override
        public int find(int p) {
            while (true) {
                eachLoopAccessTimes++;
                if (p != id[p]) {
                    eachLoopAccessTimes++;
                    p = id[p];
                } else break;
            }
            return p;
        }

        @Override
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        @Override
        public boolean allConnected() {
            return count == 1;
        }

        @Override
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            id[pRoot] = qRoot;
            eachLoopAccessTimes++;
            accessTotalTimes += eachLoopAccessTimes;
            count--;
        }

        @Override
        public void cleanPrevAccess() {
            eachLoopAccessTimes = 0;
        }

        @Override
        public int prevAccess() {
            return eachLoopAccessTimes;
        }

        @Override
        public int totalAccess() {
            return accessTotalTimes;
        }
    }

    static class WQU implements UF {

        private int[] id;
        private int[] sz;
        private int count;
        private int accessTotalTimes;
        private int eachLoopAccessTimes;

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
            while (true) {
                eachLoopAccessTimes++;
                if (p != id[p]) {
                    eachLoopAccessTimes++;
                    p = id[p];
                } else break;
            }
            return p;

        }

        @Override
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        @Override
        public boolean allConnected() {
            return count == 1;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                eachLoopAccessTimes += 2;
                if (sz[pRoot] < sz[qRoot]) {
                    id[pRoot] = qRoot;
                    sz[qRoot] += sz[pRoot];
                } else {
                    id[qRoot] = pRoot;
                    sz[pRoot] += sz[qRoot];
                }
                eachLoopAccessTimes += 4;
            }
            accessTotalTimes += eachLoopAccessTimes;
            count--;
        }

        @Override
        public void cleanPrevAccess() {
            eachLoopAccessTimes = 0;
        }

        @Override
        public int prevAccess() {
            return eachLoopAccessTimes;
        }

        @Override
        public int totalAccess() {
            return accessTotalTimes;
        }
    }

    public static void main(String[] args) throws Exception{
        //amortizedDraw(1000,QF.class);
        //amortizedDraw(1000,QU.class);
        amortizedDraw(1000,WQU.class);
    }

}
