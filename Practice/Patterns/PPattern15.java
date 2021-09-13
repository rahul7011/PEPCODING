import java.util.Scanner;

public class PPattern15 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int nsp=n+1;
        int nst=1;
        for(int i=1;i<=2*n;i++)
        {
            for(int j=1;j<=nsp;j++)
            {
                System.out.print(" ");
            }
            for(int j=1;j<=nst;j++)
            {
                if(j==1 || j==nst)
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            if(i<n)
            {
                nsp--;
                nst+=2;
            }
            else
            {
                nsp++;
                nst-=2;
            }
            System.out.println();
        }
        scn.close();
    }
}

    //      *
    //     * *
    //    *   *
    //   *     *
    //  *       *
    //   *     *
    //    *   *
    //     * *
    //      *