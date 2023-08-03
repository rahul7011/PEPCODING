import java.util.*;

public class removeDigits {
    private static int solve(int n, int[] dp) {
        if (n <= 9) {
            if (n == 0) {
                return dp[n] = 0;
            }
            return dp[n] = 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int t = n;
        int min = (int) 1e9;
        while (t != 0) {
            int num = t % 10;
            t /= 10;
            if (num == 0)
                continue;
            min = Math.min(min, solve(n - num, dp) + 1);
        }
        return dp[n] = min;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(solve(n, dp));
    }
}
