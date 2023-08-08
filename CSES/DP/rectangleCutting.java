import java.util.*;

public class rectangleCutting {
    private static int solve(int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for (int height = 1; height <= n; height++) {
            for (int width = 1; width <= m; width++) {
                if (height == width) {
                    dp[height][width] = 0;
                    continue;
                }
                int min = (int) 1e9;
                for (int x = 1; x < width; x++) {
                    min = Math.min(min, 1 + dp[height][x] + dp[height][width - x]);
                }
                for (int x = 1; x < height; x++) {
                    min = Math.min(min, 1 + dp[height - x][width] + dp[x][width]);
                }
                dp[height][width] = min;
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        System.out.println(solve(n, m));
    }
}
