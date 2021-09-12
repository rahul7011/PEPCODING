import java.util.Scanner;

public class Pattern5 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int nst=1;
        for(int i=1;i<=2*n;i++)
        {
            for(int j=1;j<=nst;j++)
            {
                System.out.print("*");
            }
            if(i<n)
            {
                nst++;
            }
            else
            {
                nst--;
            }
            System.out.println();
        }
        scn.close();
    }
}


//for n=5;
// *
// **
// ***
// ****
// *****
// ****
// ***
// **
// *