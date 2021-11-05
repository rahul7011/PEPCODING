import java.util.Scanner;

public class PrintSubsequence {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        printSS(s, "");
        scn.close();
    }

    public static void printSS(String s, String asf) {
        // if (s.length() < 0) {
        //     return;
        // }
        if (s.length() == 0) {
            System.out.println(asf);
            return;
        }
        char fchar=s.charAt(0);
        String remaiString = s.substring(1);
        printSS(remaiString, asf+fchar);
        printSS(remaiString, asf);
    }
}
