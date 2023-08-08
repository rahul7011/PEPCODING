import java.util.*;

public class editDistance {
    private static int solve(String s, String t, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0) {
                return dp[n][m] = 0;
            }
            return n == 0 ? m : n;
        }
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        int min = (int) 1e9;
        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            min = Math.min(min, solve(s, t, n - 1, m - 1, dp));
        } else {
            int add = solve(s, t, n, m - 1, dp);
            int remove = solve(s, t, n - 1, m, dp);
            int replace = solve(s, t, n - 1, m - 1, dp);
            min = Math.min(min, Math.min(add, Math.min(remove, replace)) + 1);
        }
        return dp[n][m] = min;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        String t = scn.next();
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(solve(s, t, s.length(), t.length(), dp));
    }
}
