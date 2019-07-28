package homework2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class ex10 {
    static class VisualCounter {
        /*
         * 操作最大次数
         */
        private final int N;
        /*
         * 计数器最大绝对值
         */
        private final int max;
        //自增或自减的值
        private int value;
        //执行的次数变量
        private int opCount;

        public VisualCounter(int N, int max) {
            this.N = N;
            //计数器最大绝对值
            this.max = Math.abs(max);

            //x轴为操作的最大次数
            StdDraw.setXscale(0, N);
            //y轴为计数器的最大值+10，最大值的负数-10
            StdDraw.setYscale(-max - 10, max + 10);
            //设置笔芯的弧度
            StdDraw.setPenRadius(0.01);

            StdDraw.setPenColor(StdDraw.RED);
            //画两条线
            StdDraw.line(0, max / 2, N + 1, max / 2);
            StdDraw.line(0, -max / 2, N + 1, -max / 2);

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.0048);
        }

        //自增
        public void increment() {
            if (opCount < N) {
                opCount++;
                if (value < max)
                    value++;
            }
            StdDraw.point(opCount, value);
        }

        //自减
        public void decrement() {
            if (opCount < N) {
                opCount++;
                if (value > -max)
                    value--;
            }
            StdDraw.point(opCount, value);
        }

        public String toString() {
            return "VisualCounter " + value;
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        int max = 100;
        VisualCounter counter = new VisualCounter(N, max);
        /*
         * 让计数器一半概率递增，一半概率递减
         */
        double incrementProbability = 0.5;
        for (int i = 0; i < N; i++)
            //根据给定的概率决定是自增还自减
            if (StdRandom.bernoulli(incrementProbability))
                counter.increment();
            else
                counter.decrement();
    }
}

