import java.util.Scanner;

public class PrintAllPrimesTillN {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int low = scn.nextInt();
        int high = scn.nextInt();
        for (int i = low; i <= high; i++) {
            boolean isprime = true;
            int num = i;
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
                System.out.println(i);
            }

        }

        scn.close();
    }
}
