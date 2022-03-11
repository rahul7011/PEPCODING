import java.util.Scanner;

public class CountEncodings {
    public static int solve(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < dp.length; i++) {
            int total = 0;
            if (i == 1) {
                dp[i] = s.charAt(i - 1) == '0' ? 0 : 1;
                continue;
            }
            if (s.charAt(i - 1) != '0') {
                total += dp[i - 1];
            }
            if (Integer.parseInt(s.substring(i - 2, i)) <= 26 && s.charAt(i - 2) != '0') {
                total += dp[i - 2];
            }
            dp[i] = total;
        }
        return dp[s.length()];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        System.out.println(solve(s));
        scn.close();
    }
}