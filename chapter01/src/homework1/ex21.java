package homework1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex21 {

    public static void main(String[] args) {
        StdOut.println("请输入要录入的行数：");
        int row = StdIn.readInt();
        String[][] info = new String[row][4];

        for (int i = 0; i < info.length; i++) {
            StdOut.println("请输入姓名:");
            info[i][0] = StdIn.readString();
            StdOut.println("请输入第一个数：");
            info[i][1] = String.valueOf(StdIn.readDouble());
            StdOut.println("请输入第二个数:");
            info[i][2] = String.valueOf(StdIn.readDouble());
            info[i][3] = String.valueOf(Double.parseDouble(info[i][1]) / Double.parseDouble(info[i][2]));
        }

        for (int i = 0; i < info.length; i++) {
            StdOut.println("姓名\t\t" + "num1\t\t" + "num2\t\t" + "num1/num2\t\t");
            StdOut.printf("%s\t\t%s\t\t%s\t\t%.3f", info[i][0], info[i][1], info[i][2], Float.parseFloat(info[i][3]));
        }
    }
}
