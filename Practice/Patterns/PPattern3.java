import java.util.Scanner;

public class PPattern3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int nst=n;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=nst;j++)
            {
                System.out.print("*");
            }
            System.out.println();
            nst--;
        }
        scn.close();
    }
}

// *****
// ****
// ***
// **
// *