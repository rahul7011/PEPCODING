import java.util.Scanner;

public class PrintZigzag {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        pzz(n);
        scn.close();
    }
    public static void pzz(int n)
    {
        if(n==0)
        {
            return;
        }
        System.out.print(n+" ");
        pzz(n-1);
        System.out.print(n+" ");
        pzz(n-1);
        System.out.print(n+" ");
    }
}