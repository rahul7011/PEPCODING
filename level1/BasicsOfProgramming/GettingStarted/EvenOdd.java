import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args)
    {
        //Homework is a question of printing odd and even for counting till a given number.

        Scanner scn=new Scanner(System.in);
        int num=scn.nextInt();
        int i=1;
        while(i<=num)
        {
            if(i%2==0)
            {
                System.out.println("even");
            }
            else
            {
                System.out.println("odd");
            }
            i++;
        }
        scn.close();
    }
}
