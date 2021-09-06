import java.util.Scanner;

public class RotateANumber {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int k = scn.nextInt();
        int place = 0;
        int temp = num;
        int digitCount = 0;
        while (temp != 0) {
            temp /= 10;
            digitCount++;
        }
        k = k % digitCount;
        int rotate = 0;
        while (num != 0) {
            int q = num / 10;
            int r = num % 10;
            int newpos = place + (digitCount - k);
            newpos = newpos % digitCount;
            num = q;
            place++;
            rotate = rotate + r * (int) Math.pow(10, newpos);
        }
        System.out.println(rotate);
        scn.close();
    }
}
