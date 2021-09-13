import java.util.Scanner;

public class PPattern16 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int nsp=n;
        int val=1;
        for(int i=0;i<n;i++)
        {
            for(int j=1;j<=nsp;j++)
            {
                System.out.print(" ");
            }
            int ival=val;
            for(int j=0;j<=i;j++)
            {
                System.out.print(ival+" ");
                ival=ival*(i-j)/(j+1);
            }
            System.out.println();
            nsp--;
        }
        scn.close();

    }
}
    //           1
    //         1   1
    //       1   2   1
    //     1   3   3   1
    //   1   4   6   4   1