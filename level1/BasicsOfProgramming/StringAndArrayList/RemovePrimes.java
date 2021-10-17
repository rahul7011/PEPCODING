import java.util.ArrayList;
import java.util.Scanner;

public class RemovePrimes {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        ArrayList<Integer>a=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            a.add(scn.nextInt());
        }
        removePrimes(a);
        System.out.print(a);
        scn.close();
    }
    public static void removePrimes(ArrayList<Integer>a)
    {
        for(int i=0;i<a.size();i++)
        {
            int val=a.get(i);
            if(isPrime(val)==true)
            {
                a.remove(i);
                i--;
            }
        }
    }
    public static Boolean isPrime(int n)
    {
        int i=2;
        while(i*i<=n)
        {
            if(n%i==0)
            {
                return false;
            }
            i++;
        }
        return true;
    }
}
