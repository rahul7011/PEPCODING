import java.util.Scanner;
public class ZeroOneKnapsack {

    public static int solve(int[] profit, int[] wt, int target) {
        int n = profit.length;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j - wt[i - 1]] + profit[i - 1], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] profit = new int[n];
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            profit[i] = scn.nextInt();
        }
        for (int i = 0; i < n; i++) {
            wt[i] = scn.nextInt();
        }
        int target = scn.nextInt();

        System.out.println(solve(profit, wt, target));
        scn.close();
    }
}