import java.util.Scanner;

public class TargetSumSubsets {

    public static boolean solve(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = false;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[arr.length][target];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int target = scn.nextInt();
        System.out.println(solve(arr, target));
        scn.close();
    }
}