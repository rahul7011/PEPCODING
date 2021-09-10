import java.util.Scanner;

public class Pattern10 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int nPlusOne = n / 2 + 1;
        int nMinOne = n / 2 + 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(j==nPlusOne || j==nMinOne)
                {
                    System.out.print("*\t");
                }
                else
                {
                    System.out.print("\t");
                }
            }
            if(i<=n/2)
            {
                nMinOne--;
                nPlusOne++;
            }
            else
            {
                nMinOne++;
                nPlusOne--;
            }
            System.out.println();
        }
        scn.close();
    }

}
