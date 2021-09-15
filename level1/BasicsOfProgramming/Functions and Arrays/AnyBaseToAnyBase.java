import java.util.Scanner;

public class AnyBaseToAnyBase {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sourceBase = scn.nextInt();
        int destBase = scn.nextInt();
        // Inorder to convert from anybase to anybase, we need to convert
        // anybase to decimal
        // and from decimal
        // decimal to anybase
        // can't convert directly
        int finalres = anyBaseToAnyBase(n, sourceBase, destBase);
        System.out.println(finalres);
        scn.close();
    }

    public static int anyBaseToAnyBase(int n, int sourceBase, int destBase) {
        int decimal = anyBaseToDecimal(n, sourceBase);
        int finalres = decimalToAnyBase(decimal, destBase);
        return finalres;
    }

    public static int anyBaseToDecimal(int n, int base) {
        int res = 0;
        int multiple = 1;// base^0
        while (n > 0) {
            int rem = n % 10;
            n /= 10;
            res = res + rem * multiple;
            multiple *= base;
        }
        return res;
    }

    public static int decimalToAnyBase(int n, int base) {
        int res = 0;
        int multiple = 1;// 10^0
        while (n > 0) {
            int rem = n % base;
            n /= base;
            res += rem * multiple;
            multiple *= 10;
        }
        return res;
    }

}
