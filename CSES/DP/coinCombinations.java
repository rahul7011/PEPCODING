import java.util.Arrays;
import java.util.Scanner;

public class coinCombinations {
    static int mod = (int) 1e9 + 7;

    private static int solve(int[] c, int target, int IDX, int[] dp) {
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < c.length; j++) {
                if (i - c[j] >= 0) {
                    dp[i] = (dp[i] + dp[i - c[j]]) % mod;
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int x = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int[] dp = new int[x + 1];
        System.out.println(solve(arr, x, 0, dp));
    }
}
