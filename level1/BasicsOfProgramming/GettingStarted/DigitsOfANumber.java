import java.util.Scanner;

public class DigitsOfANumber {
    public static void main(String[] args) {
        //form a divisor for the given number
        Scanner scn=new Scanner(System.in);
        int num=scn.nextInt();
        int temp=num;
        int div=1;
        while(temp>=10)
        {
            temp/=10;
            div*=10;
        }
        //now we have our divisor
        while(div>=1)
        {
            int q=num/div;
            int r=num%div;

            System.out.println(q);
            num=r;
            div/=10;
        }
        scn.close();
    }
}

// input=65784383
// output
// 6
// 5
// 7
// 8
// 4
// 3
// 8