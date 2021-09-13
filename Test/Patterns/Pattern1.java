import java.util.Scanner;

public class Pattern1 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        // write your code here
        int n = scn.nextInt();
        int val = 1;
        for (int i = 0; i < n; i++) {
            int ival = val;
            for (int j = 0; j <= i; j++) {
                System.out.print(ival + "	");
                ival = (ival * (i - j)) / (j + 1);
            }
            System.out.println();
        }
        scn.close();
    }
}

// n=5
//Pascal triangle
// 1
// 1       1
// 1       2       1
// 1       3       3       1
// 1       4       6       4       1