import java.util.Scanner;

public class ReverseANumber {
    public static void main(String[] args) {
        //Homework
        Scanner scn=new Scanner(System.in);
        int num=scn.nextInt();
        while(num!=0)
        {
            int r=num%10;
            System.out.println(r);
            num/=10;
        }
        scn.close();
    }
}
