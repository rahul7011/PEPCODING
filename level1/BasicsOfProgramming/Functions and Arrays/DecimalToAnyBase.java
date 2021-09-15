import java.util.Scanner;

public class DecimalToAnyBase {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n=scn.nextInt();
        int b=scn.nextInt();
        int dn = getValueInBase(n, b);
        System.out.println(dn);
        scn.close();
    }
    public static int getValueInBase(int n,int base)
    {
        int res=0;
        int multiple=1; //10^0
        while(n>0)
        {
            int rem=n%base;
            n/=base;
            res= res+rem*multiple;
            multiple*=10;
        }
        return res;
    }
}
