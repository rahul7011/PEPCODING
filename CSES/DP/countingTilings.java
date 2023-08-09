import java.util.*;

public class countingTilings {
    /*
     * dp(i,mask)=no of ways to fill cols from ith till mth given that some
     * positions are already fixed by the (i-1)th col(mask)
     * 
     */

    private static void generateNextMasks(int currMask, int row, int mask, int n, List<Integer> nextMasks) {
        if (row == n + 1) {
            nextMasks.add(mask);
            return;
        }
        if ((currMask & (1 << row)) != 0) {
            generateNextMasks(currMask, row + 1, mask, n, nextMasks);
        }
        if (row != n) {
            if ((currMask & (1 << row)) == 0 && (currMask & (1 << (row + 1))) == 0) {
                generateNextMasks(currMask, row + 2, mask, n, nextMasks);
            }
        }
        if ((currMask & (1 << row)) == 0) {
            generateNextMasks(currMask, row + 1, mask | (1 << row), n, nextMasks);
        }
    }

    private static int mod = (int) 1e9 + 7;

    private static int solve(int col, int mask, int n, int m, int[][] dp) {
        if (col == m + 1) {
            if (mask == 0) {
                return 1;
            }
            return 0;
        }
        if(dp[col][mask] !=-1)
        {
            return dp[col][mask];
        }
        int count = 0;
        List<Integer> nextMasks = new ArrayList<>();
        generateNextMasks(mask, 1, 0, n, nextMasks);
        for (int nextMask : nextMasks) {
            count = (count % mod + solve(col + 1, nextMask, n, m, dp) % mod) % mod;
        }
        return dp[col][mask] = count;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] dp = new int[1000 + 1][(1 << 11)];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(solve(1, 0, n, m, dp));
    }
}
