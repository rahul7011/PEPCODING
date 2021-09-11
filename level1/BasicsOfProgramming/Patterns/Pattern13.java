import java.util.Scanner;

public class Pattern13 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n; i++) {
            int val = 1;// iC0
            for (int j = 0; j <= i; j++) {
                System.out.print(val + "\t");
                int ival = (val * (i - j)) / (j + 1);
                val = ival;
            }
            System.out.println();
        }
        scn.close();
    }
}

// 1
// 1 1
// 1 2 1
// 1 3 3 1
// 1 4 6 4 1
