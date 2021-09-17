import java.util.Scanner;

public class PAnyBaseSubtraction {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int b = scn.nextInt();
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();

        int d = getDifference(b, n1, n2);
        System.out.println(d);
        scn.close();
    }

    public static int getDifference(int b, int n1, int n2) {
        int borrow = 0;
        int multiple = 1;// 10^0
        int finalres = 0;
        while (n1 != 0 || n2 != 0) {
            int rem1 = n2 % 10;
            int rem2 = n1 % 10;
            rem1 -= borrow;
            n2 /= 10;
            n1 /= 10;
            if (rem1 - rem2 < 0) {
                rem1 += b;
                borrow = 1;
            } else {
                borrow = 0;// reset the borrow
            }
            int subres = rem1 - rem2;
            finalres += subres * multiple;
            multiple *= 10;
        }
        return finalres;
    }

}