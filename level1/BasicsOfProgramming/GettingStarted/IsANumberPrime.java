import java.util.Scanner;

public class IsANumberPrime {
    public static void main(String[] args) {
        // To check whether the given numbers are prime or not in O(sqrt(n))
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t > 0) {
            boolean isprime = true;
            int num = scn.nextInt();
            int div = 2;
            while (div * div <= num) {
                int rem = num % div;
                if (rem == 0) {
                    isprime = false;
                    break;
                }
                div++;
            }
            if (isprime) {
                System.out.println("prime");
            } else {
                System.out.println("not prime");
            }
            t--;
        }
        scn.close();
    }
}
