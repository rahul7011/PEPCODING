import java.util.Scanner;

public class AnyBaseToDecimal {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int b = scn.nextInt();
        int d = getValueIndecimal(n, b);
        System.out.println(d);
        scn.close();
    }
    public static int getValueIndecimal(int n,int base)
    {
        int res=0;
        int multiple=1;//base^0
        while(n>0)
        {
            int rem=n%10;
            n/=10;
            res=res+rem*multiple;
            multiple*=base;
        }
        return res;
    }
}
