import java.util.Arrays;
import java.util.Scanner;

public class twoSets2 {
    private static int mod = (int) 1e9 + 7;

    private static int solve(int n, int idx, int tar, int[][] dp) {
        if (tar <= 0 || idx == n + 1) {
            return tar == 0 ? 1 : 0;
        }
        if (dp[idx][tar] != -1)
            return dp[idx][tar];
        int count = 0;
        for (int i = idx; i <= n; i++) {

            count = (count % mod + solve(n, i + 1, tar - i, dp) % mod) % mod;
        }
        return dp[idx][tar] = count % mod;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sum = n * (n + 1) / 2;
        if (sum % 2 != 0) {
            System.out.println(0);
            return;
        }
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(solve(n, 2, sum / 2, dp));
    }
}