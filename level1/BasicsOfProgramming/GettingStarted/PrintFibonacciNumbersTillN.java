import java.util.Scanner;

public class PrintFibonacciNumbersTillN {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num=scn.nextInt();
        int a=0;
        int b=1;
        for(int i=1;i<=num;i++)
        {
            System.out.println(a);
            int c=a+b;
            a=b;
            b=c;
        }
        scn.close();
    }
}
