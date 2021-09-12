import java.util.Scanner;

public class Pattern18 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int nsp=n;
        int nst=1;
        int val=1;
        for(int i=1;i<=2*n-1;i++)
        {
            for(int j=1;j<=nsp;j++)
            {
                System.out.print("*");
            }
            int ival=val;
            for(int j=1;j<nst;j++)
            {
                System.out.print(" ");
                if(j<=nst/2)
                {
                    ival--;
                }
                else
                {
                    ival++;
                }
            }
            for(int j=1;j<=nsp;j++)
            {
                System.out.print("*");
            }
            System.out.println();
            if(i<n)
            {
                nsp--;
                nst+=2;
                val++;
            }
            else
            {
                nsp++;
                nst-=2;
                val--;
            }
        }
        scn.close();
    }
}
    //   **********
    //   ****  ****
    //   ***    ***
    //   **      **
    //   *        *
    //   *        *
    //   **      **
    //   ***    ***
    //   ****  ****
    //   **********
