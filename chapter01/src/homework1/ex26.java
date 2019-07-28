package homework1;

public class ex26 {
    public static void main(String[] args) {
        //两两比较，做交换，大的放后面
        int a = 25;
        int b = 12;
        int c = 65;
        int t = 0;
        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            t = a;
            a = c;
            b = t;
        }
        if (b > c) {
            t = b;
            b = c;
            c = t;
        }
        System.out.println(a + " " + b + " " + c);
    }
}
