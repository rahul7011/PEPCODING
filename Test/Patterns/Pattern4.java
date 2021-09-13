import java.util.Scanner;

public class Pattern4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int org = n;
        n = 2 * n - 1;
        int l, r, u, d;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                l = j - 1;
                r = n - j;
                u = i - 1;
                d = n - i;
                int res = Math.min(Math.min(l, r), Math.min(u, d));

                if ((org - res) % 2 != 0) {
                    System.out.print("# ");
                } else
                    System.out.print(org - res + " ");
            }
            System.out.println();
        }
        scn.close();
    }
}

// Radial Pattern
// n=2
// 2 2 2 
// 2 # 2 
// 2 2 2

// n=4
// 4 4 4 4 4 4 4 
// 4 # # # # # 4 
// 4 # 2 2 2 # 4 
// 4 # 2 # 2 # 4 
// 4 # 2 2 2 # 4 
// 4 # # # # # 4 
// 4 4 4 4 4 4 4 