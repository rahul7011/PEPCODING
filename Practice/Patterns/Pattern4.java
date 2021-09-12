import java.util.Scanner;

public class Pattern4 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int val=1;
        for(int i=1;i<=n;i++)
        {
            int ival=val;
            for(int j=1;j<=i;j++)
            {
                System.out.print(ival+" ");
                ival++;
            }
            System.out.println();
        }
        scn.close();
    }
}

// 1
// 1 2
// 1 2 3
// 1 2 3 4
// 1 2 3 4 5
