import java.util.Scanner;
public class UnboundedKnapsack {
    public static int solve(int[] profit, int[] wt, int target) {
        int n = profit.length;
        int[] dp = new int[target + 1];
        for (int i = 1; i < target + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (wt[j] <= i) {
                    dp[i] = Math.max(dp[i], dp[i - wt[j]] + profit[j]);
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] profit = new int[n];
        for (int i = 0; i < n; i++) {
            profit[i] = scn.nextInt();
        }
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = scn.nextInt();
        }
        int target = scn.nextInt();
        System.out.println(solve(profit, wt, target));
        scn.close();
    }
}