import java.util.Scanner;

public class Pattern20 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                // print 0 then 1
                for (int j = 1; j <= i; j++) {
                    if (j % 2 == 1) {
                        System.out.print("0 ");
                    } else {
                        System.out.print("1 ");
                    }
                }
            } else {
                // print 1 then 0
                for (int j = 1; j <= i; j++) {
                    if (j % 2 == 1) {
                        System.out.print("1 ");
                    } else {
                        System.out.print("0 ");
                    }
                }
            }
            System.out.println();
        }
    }
}
