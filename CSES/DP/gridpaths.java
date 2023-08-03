import java.util.*;

public class gridpaths {
    private static int[][] dir = { { 0, 1 }, { 1, 0 } };
    private static int mod = (int) 1e9 + 7;

    private static int solve(char[][] grid, int i, int j, int[][] dp) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return dp[i][j] = 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '.') {
                count = (count % mod + solve(grid, x, y, dp) % mod) % mod;
            }
        }
        return dp[i][j] = count;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = scn.next().toCharArray();
        }
        if (grid[0][0] == '*' || grid[n - 1][n - 1] == '*') {
            System.out.println(0);
            return;
        }
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(solve(grid, 0, 0, dp));
    }
}