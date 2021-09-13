import java.util.Scanner;

public class PPattern18 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // write your code here
        // int n=scn.nextInt();
        char p = 'A';
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    System.out.print(p);
                } else {
                    System.out.print("*");
                }
            }
            p++;
            System.out.println();
        }
        scn.close();
    }
}

// Character Pattern
// n=4
// A
// BB
// C*C
// D**D

// n=5
// A
// BB
// C*C
// D**D
// E***E
