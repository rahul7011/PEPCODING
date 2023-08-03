import java.util.*;

public class bookshop {
    private static int solve(int[] price, int[] pages, int idx, int x, int[][] dp) {
        if (idx == price.length || x == 0) {
            return dp[x][idx] = 0;
        }
        if (dp[x][idx] != -1)
            return dp[x][idx];
        int max = (int) -1e9;
        if (x - price[idx] >= 0) {
            max = Math.max(max, solve(price, pages, idx + 1, x - price[idx], dp) + pages[idx]);
        }
        max = Math.max(max, solve(price, pages, idx + 1, x, dp));
        return dp[x][idx] = max;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int x = scn.nextInt();
        int[] price = new int[n];
        int[] pages = new int[n];
        for (int i = 0; i < price.length; i++) {
            price[i] = scn.nextInt();
        }
        for (int i = 0; i < pages.length; i++) {
            pages[i] = scn.nextInt();
        }
        int[][] dp = new int[x + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(solve(price, pages, 0, x, dp));
    }
}
