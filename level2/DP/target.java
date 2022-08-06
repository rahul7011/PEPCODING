import java.util.Arrays;

public class target {
    // permutation inifinte
    private static int permutation(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }
        if (dp[tar] != -1) {
            return dp[tar];
        }
        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutation(arr, tar - ele, dp);
            }
        }
        return dp[tar] = count;
    }

    private static int permutation_tabu(int[] arr, int TAR, int[] dp) {
        for (int tar = 0; tar <= TAR; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }
            int count = 0;
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    count += permutation(arr, tar - ele, dp);
                }
            }
            dp[tar] = count;
        }
        return dp[TAR];
    }

    private static void permutationCall() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int[] dp = new int[tar + 1];
        Arrays.fill(dp, -1);
        // System.out.println(permutation(arr, tar, dp));
        System.out.println(permutation_tabu(arr, tar, dp));
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
    }

    // combination infinite 2D
    private static int combination(int[] arr, int n, int tar, int[][] dp) {
        if (tar == 0 || n < 0) {
            if (tar == 0) {
                return dp[n][tar] = 1;
            }
            return 0;
        }
        if (dp[n][tar] != -1) {
            return dp[n][tar];
        }
        int count = 0;
        for (int i = n; i > 0; i--) {
            if (tar - arr[i - 1] >= 0) {
                count += combination(arr, i, tar - arr[i - 1], dp);
            }
        }
        return dp[n][tar] = count;
    }

    // combination infinite 1D
    private static int Combination1D(int[] arr, int[] dp) {
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            for (int tar = ele; tar < dp.length; tar++) {
                dp[tar] += dp[tar - ele];
            }
        }
        return dp[dp.length - 1];
    }

    private static void combinationCall() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        // int[][] dp = new int[arr.length + 1][tar + 1];
        // for (int a[] : dp) {
        // Arrays.fill(a, -1);
        // }
        // System.out.println(combination(arr, arr.length, tar, dp));
        int[] dp = new int[tar + 1];
        System.out.println(Combination1D(arr, dp));
    }

    // 377. Combination Sum IV
    // https://leetcode.com/problems/combination-sum-iv/
    class Solution {
        private int permutationInfinite(int[] coins, int tar, int[] dp) {
            if (tar == 0) {
                return 1;
            }
            if (dp[tar] != -1) {
                return dp[tar];
            }
            int count = 0;
            for (int ele : coins) {
                if (tar - ele >= 0) {
                    count += permutationInfinite(coins, tar - ele, dp);
                }
            }
            return dp[tar] = count;
        }

        private int permutationInfinite_tabu(int[] coins, int TAR, int[] dp) {
            for (int tar = 0; tar < dp.length; tar++) {
                if (tar == 0) {
                    dp[tar] = 1;
                    continue;
                }
                int count = 0;
                for (int ele : coins) {
                    if (tar - ele >= 0) {
                        count += dp[tar - ele];
                    }
                }
                dp[tar] = count;
            }
            return dp[TAR];
        }

        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            // Arrays.fill(dp,-1);
            // return permutationInfinite(nums,target,dp);
            return permutationInfinite_tabu(nums, target, dp);
        }
    }

    // 322. Coin Change
    // https://leetcode.com/problems/coin-change/
    class Solution1 {
        // coin change combination infinite using single dimension(1D DP)
        private static int combination(int[] coins, int[] dp) {
            dp[0] = 0;
            for (int ele : coins) {
                for (int tar = ele; tar < dp.length; tar++) {
                    dp[tar] = Math.min(dp[tar], dp[tar - ele] + 1);
                }
            }
            return dp[dp.length - 1] == (int) 1e9 ? -1 : dp[dp.length - 1];
        }

        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, (int) 1e9);
            return combination(coins, dp);
        }
    }

    // https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
    class Solution2 {
        // coin change combination infinite using single dimension(1D DP)
        private static int combination(int[] coins, int[] dp) {
            dp[0] = 0;
            for (int ele : coins) {
                for (int tar = ele; tar < dp.length; tar++) {
                    dp[tar] = Math.min(dp[tar], dp[tar - ele] + 1);
                }
            }
            return dp[dp.length - 1] == (int) 1e9 ? -1 : dp[dp.length - 1];
        }

        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, (int) 1e9);
            return combination(coins, dp);
        }
    }

    // https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
    class Solution3 {
        // Function to return max value that can be put in knapsack of capacity W.
        public static int solve(int w, int[] wt, int[] val, int n) {
            if (n == 0 || w == 0) {
                return 0;
            }
            int a1 = 0, a2 = 0;
            if (wt[n - 1] <= w) {
                a1 = val[n - 1] + solve(w - wt[n - 1], wt, val, n - 1);
            }
            a2 = solve(w, wt, val, n - 1);
            return Math.max(a1, a2);
        }

        public static int solve_memo(int w, int[] wt, int[] val, int n, int[][] dp) {
            if (n == 0 || w == 0) {
                return dp[n][w] = 0;
            }
            if (dp[n][w] != -1) {
                return dp[n][w];
            }
            int a1 = 0, a2 = 0;
            if (wt[n - 1] <= w) {
                a1 = val[n - 1] + solve_memo(w - wt[n - 1], wt, val, n - 1, dp);
            }
            a2 = solve_memo(w, wt, val, n - 1, dp);
            return dp[n][w] = Math.max(a1, a2);
        }

        public static int solve_tabu(int W, int[] wt, int[] val, int N) {
            int[][] dp = new int[N + 1][W + 1];
            for (int n = 0; n <= N; n++) {
                for (int w = 0; w <= W; w++) {
                    if (n == 0 || w == 0) {
                        dp[n][w] = 0;
                        continue;
                    }
                    int a1 = 0, a2 = 0;
                    if (wt[n - 1] <= w) {
                        a1 = val[n - 1] + dp[n - 1][w - wt[n - 1]];
                    }
                    a2 = dp[n - 1][w];
                    dp[n][w] = Math.max(a1, a2);
                }
            }
            return dp[N][W];
        }

        static int knapSack(int W, int wt[], int val[], int n) {
            // return solve(W,wt,val,n);
            // int[][] dp=new int[n+1][W+1];
            // for(int[] row:dp)
            // Arrays.fill(row,-1);
            // return solve_memo(W,wt,val,n,dp);
            return solve_tabu(W, wt, val, n);
        }
    }

    // Knapsack with Duplicate Items(unbounded knapsack)
    // https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
    class Solution4 {
        public static int solve(int w, int[] wt, int[] val, int n) {
            if (n == 0 || w == 0) {
                return 0;
            }
            int a1 = 0, a2 = 0;
            if (wt[n - 1] <= w) {
                a1 = val[n - 1] + solve(w - wt[n - 1], wt, val, n);
            }
            a2 = solve(w, wt, val, n - 1);
            return Math.max(a1, a2);
        }

        public static int solve_memo(int w, int[] wt, int[] val, int n, int[][] dp) {
            if (n == 0 || w == 0) {
                return dp[n][w] = 0;
            }
            if (dp[n][w] != -1) {
                return dp[n][w];
            }
            int a1 = 0, a2 = 0;
            if (wt[n - 1] <= w) {
                a1 = val[n - 1] + solve_memo(w - wt[n - 1], wt, val, n, dp);
            }
            a2 = solve_memo(w, wt, val, n - 1, dp);
            return dp[n][w] = Math.max(a1, a2);
        }

        public static int solve_tabu(int W, int[] wt, int[] val, int N) {
            int[][] dp = new int[N + 1][W + 1];
            for (int n = 0; n <= N; n++) {
                for (int w = 0; w <= W; w++) {
                    if (n == 0 || w == 0) {
                        dp[n][w] = 0;
                        continue;
                    }
                    int a1 = 0, a2 = 0;
                    if (wt[n - 1] <= w) {
                        a1 = val[n - 1] + dp[n][w - wt[n - 1]];
                    }
                    a2 = dp[n - 1][w];
                    dp[n][w] = Math.max(a1, a2);
                }
            }
            return dp[N][W];
        }

        static int knapSack(int n, int W, int val[], int wt[]) {
            // return solve(W,wt,val,n);
            int[][] dp = new int[n + 1][W + 1];
            // for(int[] row:dp)
            // Arrays.fill(row,-1);
            // return solve_memo(W,wt,val,n,dp);
            return solve_tabu(W, wt, val, n);
        }
    }

    public static boolean targetSum_DP(int[] arr, int N, int Tar, boolean[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    // back Engineering
    public static int targetSum_path(int[] arr, int N, boolean[][] dp, int tar, String psf) {
        if (N == 0 || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[N - 1] >= 0 && dp[N - 1][tar - arr[N - 1]])
            count += targetSum_path(arr, N - 1, dp, tar - arr[N - 1], psf + arr[N - 1] + " ");
        if (dp[N - 1][tar])
            count += targetSum_path(arr, N - 1, dp, tar, psf);

        return count;
    }

    public static void targetSum_backEngg() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10, N = 4;
        boolean[][] dp = new boolean[N + 1][tar + 1];
        System.out.println(targetSum_DP(arr, N, tar, dp));
        System.out.println(targetSum_path(arr, N, dp, tar, ""));
    }

    // Find number of solutions of a linear equation of n variables(Easy
    // Question,just remember the basics--->(Coin change combination infinite))
    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/

    // 416. Partition Equal Subset Sum
    class Solution5 {

        private boolean canPartition(int[] nums, int idx, int tar, Boolean[][] dp) {
            if (idx == nums.length || tar == 0) {
                if (tar == 0) {
                    return dp[idx][tar] = true;
                }
                return dp[idx][tar] = false;
            }
            if (dp[idx][tar] != null) {
                return dp[idx][tar];
            }
            if (tar - nums[idx] >= 0) {
                boolean check = canPartition(nums, idx + 1, tar - nums[idx], dp);
                if (check == true) {
                    return dp[idx][tar] = true;
                }
            }
            return dp[idx][tar] = canPartition(nums, idx + 1, tar, dp);
        }

        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int x : nums) {
                sum += x;
            }
            if (sum % 2 != 0) {
                return false;
            } else {
                Boolean[][] dp = new Boolean[nums.length + 1][sum / 2 + 1];
                return canPartition(nums, 0, sum / 2, dp);
            }
        }
    }

    // 494. Target Sum
    // Good Question:Teaches how to handle negative targets(by shifting logic)
    class Solution6 {
        private int findTargetSumWays(int[] nums, int n, int tar) {
            if (n == 0) {
                if (tar == 0) {
                    return 1;
                }
                return 0;
            }
            int count = 0;
            // taking as a positive number
            count += findTargetSumWays(nums, n - 1, tar - nums[n - 1]);
            // taking as a negative number
            count += findTargetSumWays(nums, n - 1, tar - (-nums[n - 1]));
            return count;
        }

        private int findTargetSumWays_memo(int[] nums, int n, int sum, int tar, int[][] dp) {
            if (n == 0) {
                if (tar == sum) {
                    return dp[n][sum] = 1;
                }
                return dp[n][sum] = 0;
            }
            if (dp[n][sum] != -1) {
                return dp[n][sum];
            }
            int count = 0;
            // taking as a positive number
            count += findTargetSumWays_memo(nums, n - 1, sum + nums[n - 1], tar, dp);
            // taking as a negative number
            count += findTargetSumWays_memo(nums, n - 1, sum - nums[n - 1], tar, dp);
            return dp[n][sum] = count;
        }

        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int x : nums) {
                sum += x;
            }
            if (target > sum || target < -sum) {
                return 0;
            }
            int[][] dp = new int[nums.length + 1][2 * sum + 1];
            // return findTargetSumWays(nums,nums.length,target);
            for (int[] d : dp)
                Arrays.fill(d, -1);
            return findTargetSumWays_memo(nums, nums.length, sum, target + sum, dp);
        }
    }

    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/submissions/
    // 698. Partition to K Equal Sum Subsets
    class Solution7 {
        // TLE
        // private boolean canPartitionKSubsets(int[] nums,int idx,int sum,int[] set)
        // {
        // if(idx==nums.length)
        // {
        // for(int i=1;i<set.length;i++)
        // {
        // if(set[i-1]!=set[i])
        // {
        // return false;
        // }
        // }
        // return true;
        // }
        // boolean check=false;
        // for(int choice=0;choice<set.length;choice++)
        // {
        // if((set[choice]+nums[idx])<=(sum / set.length))
        // {
        // set[choice]+=nums[idx];
        // check=canPartitionKSubsets(nums,idx+1,sum,set);
        // if(check==true)
        // {
        // return true;
        // }
        // set[choice]-=nums[idx];
        // }

        // }
        // return check;
        // }
        private boolean canPartitionKSubsets(int[] nums, int idx, int k, int sumSF, int tar, boolean[] visited) {
            if (k == 0) {
                return true;
            }
            if (sumSF > tar)
                return false;
            if (sumSF == tar) {
                // only k-1 more sets to go
                return canPartitionKSubsets(nums, 0, k - 1, 0, tar, visited);
            }
            boolean check = false;
            for (int i = idx; i < nums.length; i++) {
                if (visited[i] == false) {
                    visited[i] = true;
                    check = check || canPartitionKSubsets(nums, i + 1, k, sumSF + nums[i], tar, visited);
                    visited[i] = false;
                }
            }
            return check;
        }

        public boolean canPartitionKSubsets(int[] nums, int k) {
            // int[] set=new int[k];
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int x : nums) {
                sum += x;
                max = Math.max(max, x);
            }
            if (sum % k != 0 || max > sum / k) {
                return false;
            }
            // return canPartitionKSubsets(nums,0,sum,set);
            boolean[] visited = new boolean[nums.length];
            return canPartitionKSubsets(nums, 0, k, 0, sum / k, visited);

        }
    }

    // 688. Knight Probability in Chessboard
    class Solution8 {
        int dx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int dy[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

        private double knightProb(int n, int k, int r, int c) {
            if (k == 0) {
                return 1.0;
            }
            double count = 0.0;
            for (int d = 0; d < 8; d++) {
                int x = r + dx[d];
                int y = c + dy[d];
                if (x >= 0 && y >= 0 && x < n && y < n) {
                    count += knightProb(n, k - 1, x, y);
                }
            }
            return count / 8.0;
        }

        private double knightProb_memo(int n, int k, int r, int c, double[][][] dp) {
            if (k == 0) {
                return dp[k][r][c] = 1.0;
            }
            if (dp[k][r][c] != 0.0) {
                return dp[k][r][c];
            }
            double count = 0.0;
            for (int d = 0; d < 8; d++) {
                int x = r + dx[d];
                int y = c + dy[d];
                if (x >= 0 && y >= 0 && x < n && y < n) {
                    count += knightProb_memo(n, k - 1, x, y, dp);
                }
            }
            return dp[k][r][c] = count / 8.0; // divide by 8 bcoz we have 8 choices to select to
        }

        public double knightProbability(int n, int k, int row, int column) {
            // return knightProb(n,k,row,column);
            double[][][] dp = new double[k + 1][n + 1][n + 1];
            return knightProb_memo(n, k, row, column, dp);
        }
    }

    // 576. Out of Boundary Paths
    class Solution9 {
        private int mod = (int) 1e9 + 7;

        private int findPaths(int m, int n, int maxMove, int r, int c, int[][] dir, int[][][] dp) {
            if (maxMove == 0) {
                return dp[r][c][maxMove] = 0;
            }
            int count = 0;
            if (dp[r][c][maxMove] != -1) {
                return dp[r][c][maxMove];
            }
            for (int d = 0; d < dir.length; d++) {
                int x = dir[d][0] + r;
                int y = dir[d][1] + c;
                if (x >= 0 && y >= 0 && x < m && y < n) {
                    count = ((count % mod) + findPaths(m, n, maxMove - 1, x, y, dir, dp) % mod) % mod;
                    continue;
                }
                count++;
            }
            return dp[r][c][maxMove] = (count % mod);
        }

        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
            int[][][] dp = new int[m][n][maxMove + 1];
            for (int[][] d1 : dp) {
                for (int[] d : d1) {
                    Arrays.fill(d, -1);
                }
            }
            return findPaths(m, n, maxMove, startRow, startColumn, dir, dp);
        }
    }

    public static void main(String[] args) {
        // permutationCall();
        // combinationCall();
        // targetSum_backEngg();
    }
}
