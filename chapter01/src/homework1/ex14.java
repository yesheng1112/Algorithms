package homework1;

public class ex14 {

    public static void main(String[] args) {
        System.out.println(lg(1));
    }

    static int lg(double N) {
        int k = 0;
        if (N > 0 && N < 1) {
            double count = 1;
            while (count >= N) {
                count *= 1 / 2.0;
                k -= 1;
            }
            return k + 1;
        } else if (N >= 1) {
            int count = 1;
            while (count <= N) {
                count *= 2;
                k += 1;
            }
            return k - 1;
        } else
            return -1;

    }
}
