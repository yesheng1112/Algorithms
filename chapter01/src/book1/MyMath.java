package book1;


public class MyMath {

    /*
     * 判断一个数是素数吗？
     * 素数的定义：1，2除外，当一个数只能被1和自身的数整除的时候叫做素数，也叫质数
     * */
    public static boolean isPrime(int num){
        if (num<2) return false;
        for (int i = 2; i*i <=num; i++)
            if (num%i==0) return false;
        return true;
    }

    /*
    * 计算平方根(牛顿迭代法)
    * */
    public static double sqrt(double c){
        if (c<0) return Double.NaN;//0.0d / 0.0
        double err = 1e-15;//double的精度
        double t = c;
        while (Math.abs(t-c/t)>err*t)
            t=(c/t+t)/2.0;
        return t;
    }

    public static void main(String[] args) {
        /*boolean flag = myMath.isPrime(6);
        System.out.println("6是素数吗？"+flag);*/

        double result = MyMath.sqrt(4.0);
        System.out.println("4.0的平方根："+result);


    }
}
