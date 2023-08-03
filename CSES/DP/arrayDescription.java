import java.util.*;

public class arrayDescription {
    private static int mod = (int) 1e9 + 7;

    private static int solve(int[] arr, int m, int idx, int prev, int[][] dp) {
        if (idx == arr.length) {
            return dp[idx][prev] = 1;
        }
        if (dp[idx][prev] != -1)
            return dp[idx][prev];
        int count = 0;
        if (arr[idx] == 0) {
            for (int i = 1; i <= m; i++) {
                if (idx == 0) {
                    count = (count % mod + solve(arr, m, idx + 1, i, dp) % mod) % mod;
                } else if (Math.abs(prev - i) <= 1) {
                    count = (count % mod + solve(arr, m, idx + 1, i, dp) % mod) % mod;
                }
            }
        } else if (Math.abs(prev - arr[idx]) <= 1) {
            count = (count % mod + solve(arr, m, idx + 1, arr[idx], dp) % mod) % mod;
        }
        return dp[idx][prev] = count;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(solve(arr, m, 0, arr[0], dp));
    }
}
