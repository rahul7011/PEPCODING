import java.util.Scanner;

public class CountBinaryStrings {
    // include-exclude approach
    public static int solve(int n) {
        int oldInclude = 1;
        int oldExclude = 1;
        for (int i = 2; i <= n; i++) {
            int newInclude = oldExclude;
            int newExclude = oldExclude + oldInclude;

            oldExclude = newExclude;
            oldInclude = newInclude;

        }
        return (oldInclude + oldExclude);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(solve(n));
        scn.close();
    }

}