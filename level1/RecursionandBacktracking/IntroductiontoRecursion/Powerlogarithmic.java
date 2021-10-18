import java.util.Scanner;

public class Powerlogarithmic {
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
        int fnb2=power(x, n/2);
        int fn=fnb2*fnb2;
        if(n%2!=0)
        {
            fn*=x;
        }
        return fn;
    }
}
