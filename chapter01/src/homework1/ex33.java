package homework1;

import edu.princeton.cs.algs4.StdOut;

public class ex33 {

    //矩阵库
    static class Matrix {

        /**
         * 求向量的点乘
         *
         * @param x 向量一
         * @param y 向量二
         * @return 向量的点乘
         */
        static double dot(double[] x, double[] y) {
            if (x == null || y == null || x.length != y.length)
                throw new RuntimeException("两个向量的长度应该一致");
            double sum = 0;
            for (int i = 0; i < x.length; i++)
                sum += x[i] * y[i];
            return sum;
        }

        /**
         * @param a 矩阵一
         * @param b 矩阵二
         * @return 矩阵和矩阵的乘积
         */
        static double[][] mult(double[][] a, double[][] b) {
            if (a == null || b == null)
                throw new RuntimeException("矩阵不能为空");
            if (a.length * a[0].length != b.length * b[0].length)
                throw new RuntimeException("矩阵无法相乘");
            int row = a.length > b.length ? b.length : a.length;
            int col = a[0].length > b[0].length ? b[0].length : a[0].length;
            double[][] result = new double[row][col];
            double[][] transpose = a.length > b.length ? transpose(b) : transpose(a);
            if (a.length > b.length) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        result[i][j] = dot(a[j], transpose[i]);
                    }
                }
            } else {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        result[i][j] = dot(b[j], transpose[i]);
                    }
                }
            }
            return result;
        }

        /**
         * 转置矩阵
         *
         * @param a 要转置的矩阵
         * @return 转置好的矩阵
         */
        static double[][] transpose(double[][] a) {
            int row = a[0].length;
            int col = a.length;
            double[][] result = new double[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    result[i][j] = a[j][i];
                }
            }
            return result;
        }

        //矩阵和向量之积
        static double[][] mult(double[][] a, double[] x) {
            if (a == null || x == null || a[0].length != 1)
                throw new RuntimeException("Fatal Error a = " + a + " x = " + x);

            // 分配空间
            double[][] result = new double[a.length][];
            for (int i = 0; i < result.length; i++)
                result[i] = new double[x.length];

            // 矩阵相乘
            for (int i = 0; i < result.length; i++)
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = a[i][0] * x[j];
                }
            return result;

        }

        //向量和矩阵之积
        static double[] mult(double[] y, double[][] a) {
            if (y == null || a == null || y.length != a[0].length)
                throw new RuntimeException("Fatal Error y = " + y + " a = " + a);

            //分配空间
            double[] result = new double[a[0].length];

            //矩阵相乘
            for (int i = 0; i < result.length; i++) {
                double sum = 0;
                for (int j = 0; j < y.length; j++) {
                    sum += y[j] * a[j][i];
                }
                result[i] = sum;
            }
            return result;
        }
    }

    /*
     * 打印二维数组
     */
    private static void printArr(double[][] a) {
        for (double[] doubles : a) {
            for (int j = 0; j < a[0].length; j++)
                StdOut.print(doubles[j] + "  ");
            StdOut.println();
        }
        StdOut.println("====================\n");
    }

    /*
     * 打印一维数组
     */
    private static void printArr(double[] a) {
        for (double v : a) StdOut.print(v + "  ");
        StdOut.println();
        StdOut.println("====================\n");
    }

    public static void main(String[] args) {
        double[] A0 = {1, 2, 3, 4, 5};
        double[] A1 = {7, 8, 9, 10, 11};
        double[][] A = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        double[][] B = {
                {6, 7, 8},
                {9, 10, 11},
        };
        double[][] C = {
                {1},
                {2},
                {3}
        };
        double[] D = {3, 2, 1};
        double[][] E = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        //向量点乘
        StdOut.println(Matrix.dot(A0, A1));
        StdOut.println("====================\n");

        // 矩阵相乘
        printArr(Matrix.mult(C, D));

        // 转置矩阵
        printArr(Matrix.transpose(B));

        // 矩阵和向量之积
        printArr(Matrix.mult(C, D));

        // 向量和矩阵之积
        printArr(Matrix.mult(D, E));
    }
}
