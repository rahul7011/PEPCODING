import java.util.Scanner;

public class PPattern12 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int nst=n;
        int nsp=n/2;
        for(int i=1;i<=2*n;i++)
        {
            // System.out.println(i+" "+nst+" stars "+nsp+ " space");
            for(int j=1;j<=nsp;j++)
            {
                System.out.print(" ");
            }
            for(int j=1;j<=nst;j++)
            {
                System.out.print("* ");
            }
            if(i==n)
            {
            }
            else if(i<n)
            {
                nst--;
                nsp++;
            }
            else
            {
                nst++;
                nsp--;
            }
            System.out.println();
        }
        scn.close();
    }
}

    //  * * * * *
    //   * * * *
    //    * * *
    //     * *
    //      *
    //      *
    //     * *
    //    * * *
    //   * * * *
    //  * * * * *
