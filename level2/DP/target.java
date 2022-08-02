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

    public static void main(String[] args) {
        // permutationCall();
        // combinationCall();
        targetSum_backEngg();
    }
}
