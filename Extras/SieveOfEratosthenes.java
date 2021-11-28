import java.util.Scanner;

//Complexity O(nloglogn)
public class SieveOfEratosthenes {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        primeSieve(n);
        scn.close();
    }

    public static void primeSieve(int n) {
        boolean[] primes = new boolean[n + 1]; // because arrays start from zero
        for (int i = 2; i * i < primes.length; i++) {
            if (primes[i] == false) {
                for (int ja = i; i * ja < primes.length; ja++) {
                    primes[i*ja]=true;
                }
            }
        }
        //from 2 because 0 and 1 are not prime
        for(int i=2;i<primes.length;i++)
        {
            if(primes[i]==false)
            {
                System.out.println(i);
            }
        }
    }
}
