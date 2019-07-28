package homework1;


public class ex9 {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        String s = "";
        //s = Integer.toBinaryString(N);
        //除2取余倒置
        for (int n = N; n > 0; n /= 2)
            s = (n % 2) + s;
        System.out.println(s);
    }
}
