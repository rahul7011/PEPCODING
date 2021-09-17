import java.util.Scanner;

public class AnyBaseAddition {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int b = scn.nextInt();
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();

        int d = getSum(b, n1, n2);
        System.out.println(d);
        scn.close();
    }

    public static int getSum(int b, int n1, int n2) {
        int multiple = 1;// 10^0
        int carry = 0;
        int finalres = 0;
        while (n1 != 0 || n2 != 0 || carry > 0) {
            int rem1 = n1 % 10;
            int rem2 = n2 % 10;
            n1 /= 10;
            n2 /= 10;
            int subres = rem1 + rem2 + carry;
            int subrem = subres % b;
            carry = subres / b;
            finalres = finalres + subrem * multiple;
            multiple *= 10;
        }
        return finalres;
    }
}
