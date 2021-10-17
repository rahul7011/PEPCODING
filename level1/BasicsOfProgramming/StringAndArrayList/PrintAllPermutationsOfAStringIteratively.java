import java.util.Scanner;

public class PrintAllPermutationsOfAStringIteratively {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.nextLine();
        StringBuilder str = new StringBuilder(s);
        printPermutations(str);
        scn.close();
    }

    public static void printPermutations(StringBuilder s) {
        // for n size string there will be n! permutations
        int totalPermutations = factorial(s.length());
        // System.out.print(totalPermutations);
        for (int i = 0; i < totalPermutations; i++) {
            int size = s.length();
            int val = i;
            // System.out.println(s.length());
            StringBuilder temp = new StringBuilder(s);
            StringBuilder ans = new StringBuilder();
            while (size != 0) {
                int rem = val % temp.length();
                val /= temp.length();
                char element = temp.charAt(rem);
                temp.deleteCharAt(rem);
                ans.append(element);
                // System.out.print(ans.length()+" ");
                size--;
            }
            System.out.println(ans);
        }
    }

    public static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
