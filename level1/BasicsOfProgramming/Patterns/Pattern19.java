import java.util.Scanner;

public class Pattern19 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n/2+1;j++)
            {
                if(i==1 || i==n/2+1 || j==n/2+1)
                {
                    System.out.print("*\t");
                }
                else if(i>n/2 && j==1)
                {
                    System.out.print("*\t");
                }
                else
                {
                    System.out.print("\t");
                }
            }
            for(int j=1;j<=n/2;j++)
            {
                if(j==(n/2) && i<=j)
                {
                    System.out.print("*\t");
                }
                else if(i!=n/2+1 && i!=n)
                {
                    System.out.print("\t");
                }
                else
                {
                    System.out.print("*\t");
                }
            }

            System.out.println();
        }
        scn.close();
    }
}
