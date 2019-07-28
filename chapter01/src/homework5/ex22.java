package homework5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ex22 {

    //并查集接口
    interface UF {
        int find(int p);

        boolean connected(int p, int q);

        boolean allConnected();

        void union(int p, int q);

        void reset();
    }

    /**
     * 性能测试用例
     *
     * @param T    规模加倍次数
     * @param begN 起始规模
     * @param type 并查集实现类型，一定要实现 UF 接口
     */
    static void doubleRationTest(int T, int begN, Class<?> type) throws Exception {
        double prev = 0, cur = 0;
        //规模扩大
        for (int i = begN, j = 0; j < T; i += i, j++) {
            UF uf = (UF) type.getDeclaredConstructor(int.class).newInstance(i);
            ArrayGenerator.RandomPair gen = new ArrayGenerator.RandomPair(i);
            cur = 0;
            int testCount = 10; //多次测验，取平均值
            for (int k = 0; k < testCount; k++) {
                uf.reset();//重置
                Stopwatch timer = new Stopwatch();
                while (!uf.allConnected()) {
                    int[] pair = gen.nextPair();
                    if (uf.connected(pair[0], pair[1])) continue;
                    uf.union(pair[0], pair[1]);
                }
                cur += timer.elapsedTime();
            }
            cur /= testCount * 1.0;
            StdOut.printf("规模：%d 耗时 %f 倍率： %f\n", i, cur, cur / prev);
            prev = cur;
        }
    }

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
        public boolean allConnected() {
            return count == 1;
        }

        @Override
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            for (int i = 0; i < id.length; i++)
                if (id[i] == pRoot) id[i] = qRoot;
            count--;
        }

        @Override
        public void reset() {
            for (int i = 0; i < id.length; i++)
                id[i] = i;
            count = id.length;
        }
    }

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
        public boolean allConnected() {
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

    static class WQU implements UF {

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
        public boolean allConnected() {
            return count == 1;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public void union(int p, int q) {
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

        @Override
        public void reset() {
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = id.length;
        }
    }

    public static void main(String[] args) throws Exception{
        //doubleRationTest(12, 100, QF.class);
        doubleRationTest(12, 100, QU.class);
        //doubleRationTest(12, 100, WQU.class);
    }
}
