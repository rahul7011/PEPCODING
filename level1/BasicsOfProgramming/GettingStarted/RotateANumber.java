import java.util.Scanner;

public class RotateANumber {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int k = scn.nextInt();
        // first the number of digits
        int nod = 0; // no of digits
        int temp = num;
        while (temp != 0) {
            temp /= 10;
            nod++;
        }
        // To remove the redundancy of same rotation
        k = k % nod;
        // To change the left(-ve) rotation to right rotation(+ve)
        if (k < 0) {
            k = k + nod;
        }
        // now forming divisor and multiplier;
        int div = 1;
        int mul = 1;
        for (int i = 1; i <= nod; i++) {
            if (i <= k) {
                div *= 10;
            } else {
                mul *= 10;
            }
        }
        // forming the answer using divisor and multiplier
        int q = num / div;
        int r = num % div;
        int ans = r * mul + q;
        System.out.println(ans);
        scn.close();
    }
}
