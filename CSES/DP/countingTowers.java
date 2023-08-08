import java.util.*;

public class countingTowers {
    private static long mod = (int) 1e9 + 7;

    private static long solve(int n, long[][] dp) {
        // dp[i]=ways to fill from i till n
        // 1->i-1th position has a tile of width 2
        // 0->i-th position has 2 tiles of width 1
        dp[n + 1][0] = 1;
        dp[n + 1][1] = 1;
        for (int i = n; i >= 2; i--) {
            // do not extend any tile
            long op1 = (dp[i + 1][0] % mod + dp[i + 1][1] % mod) % mod;
            // combine both
            long op2 = dp[i + 1][0];
            // extend 1 only
            long op3 = (2 * dp[i + 1][0]) % mod;
            // extend the tile having 2 as a width
            long op4 = dp[i + 1][1];
            dp[i][0] = (op1 % mod + op2 % mod + op3 % mod) % mod;
            dp[i][1] = (op1 % mod + op4 % mod) % mod;
        }
        return (dp[2][1] % mod + dp[2][0] % mod)%mod;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            long[][] dp = new long[n + 2][2];
            System.out.println(solve(n, dp));
        }
    }
}
