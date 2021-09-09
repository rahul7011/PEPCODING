import java.util.Scanner;

public class Pattern9 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int nsp = 0;
        int snsp = n - 1;
        for (int i = 1; i <= n; i++) {
            // System.out.println(nsp + " space 1 star " + snsp + " space 1 star");
            for (int j = 1; j <= nsp; j++) {
                System.out.print("\t");
            }
            System.out.print("*");
            for (int j = 1; j <= snsp; j++) {
                System.out.print("\t");
            }
            if (i != (n / 2 + 1)) {

                System.out.print("*");
            }
            System.out.println();
            if (i <= n / 2) {
                nsp++;
                snsp -= 2;
            } else {
                nsp--;
                snsp += 2;
            }
        }
        scn.close();
    }
}
