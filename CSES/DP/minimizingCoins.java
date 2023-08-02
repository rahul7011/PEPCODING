import java.util.Arrays;
import java.util.Scanner;

public class minimizingCoins {
    private static int solve(int[] arr, int x, int idx, int[] dp) {
        for (int i = 1; i <= x; i++) {
            int minMoves = (int) 1e9;
            for (int j = 0; j < arr.length; j++) {
                if (i - arr[j] >= 0)
                    minMoves = Math.min(minMoves, dp[i - arr[j]] + 1);
            }
            dp[i] = minMoves;
        }
        return dp[x];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int x = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int[] dp = new int[x + 1];
        int ans = solve(arr, x, 0, dp);
        System.out.println(ans != (int) 1e9 ? ans : -1);
    }
}
