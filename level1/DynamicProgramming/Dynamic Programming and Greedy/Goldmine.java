import java.util.*;
public class Goldmine {
    public static int solve(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = arr[i][0];
        }
        for (int j = 1; j < dp[0].length; j++) {
            for (int i = 0; i < dp.length; i++) {
                if (i == 0) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j - 1]);
                } else if (i == dp.length - 1) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], Math.max(dp[i + 1][j - 1], dp[i - 1][j - 1]));
                }
                dp[i][j] += arr[i][j];
            }
        }
        // for (int i = 0; i < dp.length; i++) {
        // for (int j = 0; j < dp[0].length; j++) {
        // System.out.print(dp[i][j]+" ");
        // }
        // System.out.println();
        // }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i][dp[0].length - 1]);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        System.out.println(solve(arr));
        scn.close();
    }
}
