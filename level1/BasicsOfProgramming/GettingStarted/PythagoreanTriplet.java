import java.util.Scanner;

public class PythagoreanTriplet {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        int b = scn.nextInt();
        int c = scn.nextInt();
        // finding the greatest
        int highest;
        if (a > b) {
            if (a > c) {
                highest = a;
            } else {
                highest = c;
            }
        } else {
            if (b > c) {
                highest = b;
            } else {
                highest = c;
            }
        }
        if (highest == a) {
            if ((int) Math.pow(a, 2) == ((int) Math.pow(b, 2) + (int) Math.pow(c, 2))) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        } else if (highest == b) {
            if ((int) Math.pow(b, 2) == ((int) Math.pow(a, 2) + (int) Math.pow(c, 2))) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        } else {
            if ((int) Math.pow(c, 2) == ((int) Math.pow(b, 2) + (int) Math.pow(a, 2))) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
        scn.close();

    }
}
