import java.util.Scanner;

public class Powerlinear {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int x=scn.nextInt();
        int n=scn.nextInt();
        int ans=power(x,n);
        System.out.println(ans);
        scn.close();
    }
    public static int power(int x,int n)
    {
        if(n==0)
        {
            return 1;
        }
        int fnm1=power(x, n-1);
        int fnm=x*fnm1;
        return fnm;
    }
}
