import java.util.Arrays;

public class l2_twoPointer {

    // leetcode 62(Unique Paths)

    // Time Limit Exceeded
    public int uniquePaths_Rec(int er, int ec, int[][] dp, int[][] dir) {
        if (er == 0 && ec == 0) {
            // Indentity Value(Means a path is present)
            return 1;
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = er + dir[d][0];
            int y = ec + dir[d][1];
            if (x >= 0 && y >= 0 && x < dp.length && y < dp[0].length) {
                count += uniquePaths_Rec(x, y, dp, dir);
            }
        }
        return count;
    }

    // Memoization
    public int uniquePaths_memo(int er, int ec, int[][] dp, int[][] dir) {
        if (er == 0 && ec == 0) {
            // Indentity Value(Means a path is present)
            return dp[er][ec] = 1;
        }
        if (dp[er][ec] != 0) {
            return dp[er][ec];
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = er + dir[d][0];
            int y = ec + dir[d][1];
            if (x >= 0 && y >= 0 && x < dp.length && y < dp[0].length) {
                count += uniquePaths_memo(x, y, dp, dir);
            }
        }
        return dp[er][ec] = count;
    }

    // Tabulation
    public int uniquePaths_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    // Indentity Value(Means a path is present)
                    dp[er][ec] = 1;
                    continue;
                }
                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int x = er + dir[d][0];
                    int y = ec + dir[d][1];
                    if (x >= 0 && y >= 0 && x < dp.length && y < dp[0].length) {
                        count += dp[x][y];// (x,y,dp,dir);
                    }
                }
                dp[er][ec] = count;
            }
        }
        return dp[ER][EC];
    }

    public int uniquePaths(int m, int n) {
        int er = m - 1;
        int ec = n - 1;
        int[][] dp = new int[m][n];
        int[][] dir = { { 0, -1 }, { -1, 0 } };
        // return uniquePaths_Rec(er,ec,dp,dir);
        // return uniquePaths_memo(er,ec,dp,dir);
        return uniquePaths_tabu(er, ec, dp, dir);
    }

    // leetcode 63(Unique Paths II)

    // Memoization
    public int uniquePathsWithObstacles_memo(int er, int ec, int[][] dp, int[][] grid, int[][] dir) {
        if (er == 0 && ec == 0) {
            // Identity means a path is present
            return 1;
        }
        int count = 0;
        if (dp[er][ec] != -1) {
            return dp[er][ec];
        }
        for (int d = 0; d < dir.length; d++) {
            int x = er + dir[d][0];
            int y = ec + dir[d][1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 1) {
                count += uniquePathsWithObstacles_memo(x, y, dp, grid, dir);
            }
        }
        return dp[er][ec] = count;
    }

    // Tabulation
    public int uniquePathsWithObstacles_tabu(int ER, int EC, int[][] dp, int[][] grid, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    // Identity means a path is present
                    dp[er][ec] = 1;
                    continue;
                }
                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int x = er + dir[d][0];
                    int y = ec + dir[d][1];
                    if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 1) {
                        count += dp[x][y]; // uniquePathsWithObstacles_memo(x,y,dp,grid,dir);
                    }
                }
                dp[er][ec] = count;
            }
        }
        return dp[ER][EC];
    }

    public int uniquePathsWithObstacles(int[][] grid) {
        // we will go from end to front although we can also start and go to end
        int er = grid.length - 1;
        int ec = grid[0].length - 1;
        if (grid[0][0] == 1 || grid[er][ec] == 1) {
            return 0;
        }
        int[][] dir = { { 0, -1 }, { -1, 0 } };
        int[][] dp = new int[er + 1][ec + 1];

        for (int[] rows : dp) {
            Arrays.fill(rows, -1);
        }
        // return uniquePathsWithObstacles_memo(er,ec,dp,grid,dir);
        return uniquePathsWithObstacles_tabu(er, ec, dp, grid, dir);
    }

    // leetcode 396(Rotate Function)

    // bruteforce O(n2)
    // TLE
    public int maxRotateFunction(int[] nums, int idx) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int nidx = (idx + i) % nums.length;
            sum += (nums[nidx] * i);
        }
        return sum;
    }

    public int maxRotateFunction(int[] nums) {
        int max = (int) -1e9;
        // bruteforce
        // for(int i=0;i<nums.length;i++)
        // {
        // max=Math.max(max,maxRotateFunction(nums,i));
        // }

        // Optimised
        int currSum = 0;
        int origSum = 0;
        for (int i = 0; i < nums.length; i++) {
            origSum += nums[i];
            currSum += nums[i] * i;
        }
        max = currSum;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int newSum = currSum - origSum + n * (nums[i]);
            // System.out.println(currSum+" "+newSum);
            max = Math.max(max, newSum);
            currSum = newSum;
        }
        return max;
    }
}
