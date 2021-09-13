import java.util.Scanner;

public class PPattern9 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int nsp=1;
        int nst=2*n-1;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=nsp;j++)
            {
                System.out.print(" ");
            }
            for(int j=1;j<=nst;j++)
            {
                System.out.print("*");
            }
            nst-=2;
            nsp++;
            System.out.println();
        }
        scn.close();
    }
}

    // *********
    //  *******
    //   *****
    //    ***
    //     *
