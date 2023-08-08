import java.util.*;

public class removalGame {
    private static long solve(int[] arr, int i, int j, Long[][] dp) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long max = Long.MIN_VALUE;
        max = Math.max(max, Math.min(solve(arr, i + 2, j, dp), solve(arr, i + 1, j - 1, dp)) + arr[i]);
        max = Math.max(max, Math.min(solve(arr, i + 1, j - 1, dp), solve(arr, i, j - 2, dp)) + arr[j]);
        return dp[i][j]=max;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        Long[][] dp = new Long[n + 1][n + 1];
        System.out.println(solve(arr, 0, n - 1, dp));
    }
}
