import java.util.Scanner;

public class Pattern3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int nsp = n - 1;
        int nst = 1;
        for (int i = 0; i < n; i++) {
            // System.out.println(nsp +" spaces"+ nst+" stars");
            for (int j = 1; j <= nsp; j++) {
                System.out.print(" \t");
            }
            for (int j = 1; j <= nst; j++) {
                System.out.print("*\t");
            }
            System.out.println();
            nsp--;
            nst++;
        }
        scn.close();

    }
}
