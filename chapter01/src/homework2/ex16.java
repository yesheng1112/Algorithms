package homework2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex16 {

    //欧几里得算法求最大公约数
    static long gcd(long p, long q) {
        if (q == 0) return p;
        return gcd(q, p % q);
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
            return new Rational(numerator * b.denominator + denominator * b.numerator, denominator * b.denominator);
        }

        Rational minus(Rational b) {
            return new Rational(numerator * b.denominator - denominator * b.numerator, denominator * b.denominator);
        }

        Rational times(Rational b) {
            return new Rational(numerator * b.numerator, denominator * b.denominator);
        }

        Rational divides(Rational b) {
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

        static void printPlus(Rational r1, Rational r2) {
            StdOut.println(r1 + " + " + r2 + " = " + r1.plus(r2));
        }

        static void printMinus(Rational r1, Rational r2) {
            StdOut.println(r1 + " - " + r2 + " = " + r1.minus(r2));
        }

        static void printTimes(Rational r1, Rational r2) {
            StdOut.println(r1 + " * " + r2 + " = " + r1.times(r2));
        }

        static void printDivides(Rational r1, Rational r2) {
            StdOut.println(r1 + " / " + r2 + " = " + r1.divides(r2));
        }
    }

    public static void main(String[] args) {
        /*for(int i = 0; i < 8; i++)
            Rational.printPlus(Rational.randomRational(),
                    Rational.randomRational());
        for(int i = 0; i < 8; i++)
            Rational.printMinus(Rational.randomRational(),
                    Rational.randomRational());
        for(int i = 0; i < 8; i++)
            Rational.printTimes(Rational.randomRational(),
                    Rational.randomRational());
        for(int i = 0; i < 8; i++)
            Rational.printDivides(Rational.randomRational(),
                    Rational.randomRational());*/
        Rational.printMinus(new Rational(1, 2), new Rational(9, 2));
    }
}
