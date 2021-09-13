import java.util.Scanner;

public class PPattern14 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int nsp=n/2;
        int nst=2*n-1;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=nsp;j++)
            {
                System.out.print(" ");
            }
            for(int j=1;j<=nst;j++)
            {
                if(j==1 || j==nst || i==1)
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            nsp++;
            nst-=2;
            System.out.println();
        }
        scn.close();
    }
}
    //  *********
    //   *     *
    //    *   *
    //     * *
    //      *

