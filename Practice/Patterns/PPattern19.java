import java.util.Scanner;

public class PPattern19 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int nst=1;
        int nsp=2*n-2;
        for(int i=1;i<=2*n-1;i++)
        {
            for(int j=1;j<=nst;j++)
            {
                System.out.print("*");
            }
            for(int j=1;j<=nsp;j++)
            {
                System.out.print(" ");
            }
            for(int j=1;j<=nst;j++)
            {
                System.out.print("*");
            }
            if(i<n)
            {
                nst++;
                nsp-=2;
            }
            else
            {
                nst--;
                nsp+=2;
            }
            System.out.println();
        }
        scn.close();
    }
}

    //    *        *
    //    **      **
    //    ***    ***
    //    ****  ****
    //    **********
    //    ****  ****
    //    ***    ***
    //    **      **
    //    *        *
