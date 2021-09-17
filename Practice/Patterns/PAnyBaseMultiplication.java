import java.util.Scanner;

public class PAnyBaseMultiplication {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int b = scn.nextInt();
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();

        int d = getProduct(b, n1, n2);
        System.out.println(d);
        scn.close();
    }

    public static int getProduct(int b, int n1, int n2) {
        // finding minimum
        int min = n1;
        if (min > n2) {
            min = n2;
        }
        int max = n1;
        if (max < n2) {
            max = n2;
        }
        int carry = 0;
        int count = 1;
        int finalres = 0;
        while (min != 0) {
            int rem1 = min % 10;
            min /= 10;
            int tmax = max;
            int tempres = 0;
            int multiple = 1 * count;// 10^0
            while (tmax != 0 || carry > 0) {
                int rem2 = tmax % 10;
                tmax /= 10;
                int subres = rem1 * rem2 + carry;
                carry = subres / b;
                int subrem = subres % b;
                tempres = tempres + subrem * multiple;
                multiple *= 10;
            }
            count *= 10;
            finalres = getSum(b, finalres, tempres);
        }
        return finalres;
    }

    public static int getSum(int b, int n1, int n2) {
        int carry = 0;
        int finalres = 0;
        int multiple = 1;// 10^0
        while (n1 != 0 || n2 != 0 || carry > 0) {
            int rem1 = n1 % 10;
            int rem2 = n2 % 10;
            n1 /= 10;
            n2 /= 10;
            int subres = rem1 + rem2 + carry;
            carry = subres / b;
            int subrem = subres % b;
            finalres += subrem * multiple;
            multiple *= 10;
        }
        return finalres;
    }

}
