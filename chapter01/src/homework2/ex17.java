package homework2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex17 {

    //欧几里得算法求最大公约数
    static long gcd(long p, long q) {
        if (q == 0) return p;
        return gcd(q, p % q);
    }

    /*
     * x > 0 y > 0 x + y < 0 负溢出
     * x < 0 y < 0 x + y > 0 正溢出
     * x < 0 y > 0 不会溢出
     * x > 0 y < 0 不会溢出
     * 异或 ^ 相同取 0 不同取 1
     * 正数和正数异或结果还是正数
     * 负数和负数异或结果是正数
     * 正数和负数异或的结果是负数
     */
    static boolean addOverflow(long x, long y) {
        long r = x + y;
        return ((r ^ x) & (r ^ y)) < 0;
    }
    /*
     * x < 0 y > 0 x - y > 0 负溢出
     * x > 0 y < 0 x - y < 0 正溢出
     * x > 0 y > 0 不会溢出
     * x < 0 y < 0 不会溢出
     */
    static boolean subOverflow(long x, long y) {
        long r = x - y;
        return ((r ^ x) & (x ^ y)) < 0;
    }
    /*
     * 如果高 32 位都是 0， 不可能溢出
     * 由于溢出的截断，r / y != x
     * 排除特例
     */
    static boolean mulOverflow(long x, long y) {
        //无符号右移
        if ((x | y) >>> 31 != 0) {
            x = Math.abs(x);
            y = Math.abs(y);
            long r = x * y;
            /*
             * r / y != x 包含了 x = -1 y = Long.MinValue的情况
             * 但没有包含 x = Long.MinValue y = -1 的情况
             */
            return (y != 0 && (r / y != x)) || (x == Long.MIN_VALUE && y == -1);
        }
        return false;
    }


    static class Rational {
        //分子
        private long numerator;
        //分母
        private long denominator;

        Rational(long numerator, long denominator) {
            //起到约分的作用
            long factor = gcd(numerator, denominator);
            this.numerator = numerator / factor;
            this.denominator = denominator / factor;
        }

        Rational plus(Rational b) {
            assert !mulOverflow(numerator, b.denominator) : String.format("%d * %d overflow!", numerator, b.denominator);
            assert !mulOverflow(denominator, b.numerator) : String.format("%d * %d overflow!", denominator, b.numerator);
            assert !addOverflow(numerator * b.denominator, denominator * b.numerator) : String.format("%d + %d overflow!", numerator * b.denominator, denominator * b.numerator);
            assert !mulOverflow(denominator, b.denominator) : String.format("%d * %d overflow!", denominator, b.denominator);
            return new Rational(numerator * b.denominator + denominator * b.numerator, denominator * b.denominator);
        }

        Rational minus(Rational b) {
            assert !mulOverflow(numerator, b.denominator) : String.format("%d * %d overflow!", numerator, b.denominator);
            assert !mulOverflow(denominator, b.numerator) : String.format("%d * %d overflow!", denominator, b.numerator);
            assert !subOverflow(numerator * b.denominator, denominator * b.numerator) : String.format("%d - %d overflow!", numerator * b.denominator, denominator * b.numerator);
            assert !mulOverflow(denominator, b.denominator) : String.format("%d * %d overflow!", denominator, b.denominator);
            return new Rational(numerator * b.denominator - denominator * b.numerator, denominator * b.denominator);
        }

        Rational times(Rational b) {
            assert !mulOverflow(numerator, b.numerator) : String.format("%d * %d overflow!", numerator, b.numerator);
            assert !mulOverflow(denominator, b.denominator) : String.format("%d * %d overflow!", denominator, b.denominator);
            return new Rational(numerator * b.numerator, denominator * b.denominator);
        }

        Rational divides(Rational b) {
            assert !mulOverflow(numerator, b.denominator) : String.format("%d * %d overflow!", numerator, b.denominator);
            assert !mulOverflow(denominator, b.numerator) : String.format("%d * %d overflow!", denominator, b.numerator);
            return new Rational(numerator * b.denominator, denominator * b.numerator);
        }

        static Rational randomRational() {
            return new Rational(StdRandom.uniform(1, 10), StdRandom.uniform(1, 10));
        }

        boolean equals(Rational that) {
            if (that == this) return true;
            if (that == null) return false;
            if (that.getClass() != this.getClass()) return false;
            return (this.denominator == that.denominator) && (this.numerator == that.numerator);
        }

        public String toString() {
            String s = String.format("【%d/%d】", Math.abs(numerator), Math.abs(denominator));
            if (denominator == 1 && numerator < 0)
                s = String.format("【-%d】", Math.abs(numerator));
            else if (denominator == 1)
                s = String.format("【%d】", numerator);
            else if (numerator < 0 || denominator < 0)
                s = "-" + s;
            return s;
        }

        static void printPlus(ex16.Rational r1, ex16.Rational r2) {
            StdOut.println(r1 + " + " + r2 + " = " + r1.plus(r2));
        }

        static void printMinus(ex16.Rational r1, ex16.Rational r2) {
            StdOut.println(r1 + " - " + r2 + " = " + r1.minus(r2));
        }

        static void printTimes(ex16.Rational r1, ex16.Rational r2) {
            StdOut.println(r1 + " * " + r2 + " = " + r1.times(r2));
        }

        static void printDivides(ex16.Rational r1, ex16.Rational r2) {
            StdOut.println(r1 + " / " + r2 + " = " + r1.divides(r2));
        }
    }

    public static void main(String[] args) {
        //StdOut.println(new Rational(Long.MAX_VALUE, 1).plus(new Rational(1, 1)));
        StdOut.println(new Rational(Long.MAX_VALUE, 1).minus(new Rational(1, 1)));
        //StdOut.println(new Rational(Long.MAX_VALUE, 1).plus(new Rational(Long.MAX_VALUE, 1)));
        StdOut.println(new Rational(Long.MAX_VALUE, 1).divides(new Rational(Long.MAX_VALUE, 1)));

    }
}
