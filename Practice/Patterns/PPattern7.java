import java.util.Scanner;

public class PPattern7 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int nst = n;
        int nsp = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= nsp; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= nst; j++) {
                System.out.print("*");
            }
            System.out.println();
            nst--;
            nsp++;
        }
        scn.close();

    }
}

// *****
// ****
// ***
// **
// *