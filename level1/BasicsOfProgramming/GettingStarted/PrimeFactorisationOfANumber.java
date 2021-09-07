import java.util.Scanner;

public class PrimeFactorisationOfANumber {
    public static void main(String[] args) {
        Scanner scn= new Scanner(System.in);
        int num=scn.nextInt();
        //outside loop divide till root N
        for(int factor=2;factor*factor<=num;factor++)
        {
            //inside loop divide with the factor as long as possible
            while(num%factor==0)
            {
                System.out.print(factor+ " ");
                num/=factor;
            }
        }
        //special case -prime
        if(num>1)
        {
            System.out.print(num);
        }
        scn.close();
    }
}
