import java.util.Scanner;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int n1=scn.nextInt();
        int n2=scn.nextInt();
        int n3=scn.nextInt();
        tower(n,n1,n2,n3);
        scn.close();
    }
    public static void tower(int n,int tower1,int tower2,int tower3)
    {
        if(n==0)
        {
            return;
        }
        tower(n-1,tower1,tower3,tower2);
        System.out.println(n+"["+tower1+" -> "+tower2+"]");
        tower(n-1,tower3,tower2,tower1);
    }
}
