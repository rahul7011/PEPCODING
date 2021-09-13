import java.util.Scanner;

public class Pattern3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int val = 1;
        for (int i = 1; i <= n; i++) {
            int ival = val;
            for (int j = 1; j <= n; j++) {
                if (i == 1 || i == n || j == 1 || j == n)
                    System.out.print(ival + " ");
                else
                    System.out.print("* ");
                ival++;
            }
            if (n % 2 == 0) {

                if (i == n / 2) {
                    val += n;
                } else {
                    if (i < n / 2) {
                        val += 2 * n;
                    } else {
                        val -= 2 * n;
                    }
                }
            } else {
                if (i == n / 2 + 1) {
                    val -= n;
                } else {
                    if (i <= n / 2)
                        val += 2 * n;
                    else
                        val -= 2 * n;
                }
            }
            System.out.println();
        }
        scn.close();
    }
}

// Hollow Square
// n=5
// 1 2 3 4 5 
// 11 * * * 15 
// 21 * * * 25 
// 16 * * * 20 
// 6 7 8 9 10 

// n=4
// 1 2 3 4 
// 9 * * 12 
// 13 * * 16 
// 5 6 7 8 