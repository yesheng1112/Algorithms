package homework1;

import edu.princeton.cs.algs4.StdOut;

public class ex24 {
    /**
     * 打印缩进
     *
     * @param indents 缩进数
     */
    private static void printIndents(final int indents) {
        for (int i = 0; i < indents; ++i)
            StdOut.print("----------");
    }

    /**
     * 打印调用信息
     *
     * @param p     数一
     * @param q     数二
     * @param depth 查找的深度
     */
    private static void printCallInfo(int p, int q, int depth) {
        StdOut.print(depth + "\t");
        printIndents(depth);
        StdOut.println(p + "   " + q);
    }

    /**
     * 使用2300多年前的欧几里得算法求解两数的最大公约数
     * 数一和数二的最大公约数等于数二和数一和数二的余数的最大公约数
     *
     * @param p 数一
     * @param q 数二
     * @return 最大公约数
     */
    public static int Euclid(int p, int q, int depth) {
        printCallInfo(p, q, depth);
        if (q == 0)
            return p;
        int r = p % q;
        return Euclid(q, r, depth + 1);
    }

    public static void main(String[] args) {
        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);
        int gcd = Euclid(p, q, 0);
        StdOut.println("\n" + p + " 和 " + q + " 的最大公约数是： " + gcd);
    }
}
