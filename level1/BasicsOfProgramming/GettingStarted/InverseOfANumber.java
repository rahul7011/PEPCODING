import java.util.Scanner;

public class InverseOfANumber {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int place = 1;
        int inv = 0;
        while (num != 0) {
            int q = num / 10;
            int r = num % 10;
            num = q;
            inv = inv + place * (int) Math.pow(10, r - 1);
            place++;
        }
        System.out.println(inv);
        scn.close();
    }
}
