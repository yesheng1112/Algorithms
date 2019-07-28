package homework1;

public class ex11 {

    public static void main(String[] args) {
        boolean[][] arrs = new boolean[][]{{true,false,true,false,true},{false,true,true,false,false},{false,false,false,true,true}};
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                if (arrs[i][j])
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
