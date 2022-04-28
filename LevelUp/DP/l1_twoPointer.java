public class l1_twoPointer {
    /*
     * 1. Faith
     * 2. Tree Diagram
     * 3. Recursion
     * 4. Recursion -> Memoization
     * 5. Obervation
     * 6. Memoization -> Tabulation after observation
     * 7. Optimization
     */

    // leetcode 1137 (N-th Tribonacci Number)

    // Recursive
    public int tribo(int n) {
        if (n <= 2) {
            return n == 0 ? 0 : 1;
        }
        return tribo(n - 1) + tribo(n - 2) + tribo(n - 3);
    }

    // memo
    public int tribo_memo(int n, int[] dp) {
        if (n <= 2) {
            return dp[n] = (n == 0) ? 0 : 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = tribo_memo(n - 1, dp) + tribo_memo(n - 2, dp) + tribo_memo(n - 3, dp);
    }

    // Tabulation
    public int tribo_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 2) {
                dp[n] = (n == 0) ? 0 : 1;
                continue;
            }
            dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]; // tribo_memo(n-1,dp)+tribo_memo(n-2,dp)+tribo_memo(n-3,dp);
        }
        return dp[N];
    }

    // Optimised
    public int tribo_opti(int N) {
        int a = 0, b = 1, c = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }
        return a;
    }

    public int tribonacci(int n) {
        int[] dp = new int[n + 1];
        return tribo_opti(n);
    }

    // leetcode 746 (Min Cost Climbing Stairs)

    // recusrive
    public int minCost_rec(int[] cost, int n) {
        if (n <= 1) {
            return cost[n];
        }
        int fcall = minCost_rec(cost, n - 1);
        int scall = minCost_rec(cost, n - 2);
        return Math.min(fcall, scall) + (n == cost.length ? 0 : cost[n]);
    }

    // Memoisation
    public int minCost_memo(int[] cost, int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int fcall = minCost_memo(cost, n - 1, dp);
        int scall = minCost_memo(cost, n - 2, dp);
        return dp[n] = Math.min(fcall, scall) + (n == cost.length ? 0 : cost[n]);
    }

    // Tabulation
    public int minCost_tabu(int[] cost, int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }
            int fcall = dp[n - 1];
            int scall = dp[n - 2];
            dp[n] = Math.min(fcall, scall) + (n == cost.length ? 0 : cost[n]);
        }
        return dp[N];
    }

    // Optimisation
    public int minCost_opti(int[] cost, int N) {
        int a = cost[0], b = cost[1];
        for (int n = 2; n < N; n++) {
            int sum = Math.min(a, b) + cost[n];
            a = b;
            b = sum;
        }
        return Math.min(a, b);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        return minCost_opti(cost, n);
    }

    // leetcode 70 (Climbing Stairs)

    // recursive
    public int climb_rec(int n) {
        if (n <= 2) {
            return n;
        }
        return climb_rec(n - 1) + climb_rec(n - 2);
    }

    // memo
    public int climb_memo(int n, int[] dp) {
        if (n <= 2) {
            return dp[n] = n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = climb_memo(n - 1, dp) + climb_memo(n - 2, dp);
    }

    // tabulation
    public int climb_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 2) {
                dp[n] = n;
                continue;
            }
            dp[n] = dp[n - 1] + dp[n - 2]; // climb_memo(n-1,dp)+climb_memo(n-2,dp);
        }
        return dp[N];
    }

    // optimisation
    public int climb_opti(int N) {
        int a = 1, b = 2;
        for (int n = 1; n < N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climb_opti(n);
    }

    // HomeWork:
    // Friends Pairing Problem
    // (https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1)
    public long mod = (int) 1e9 + 7;

    // recursive
    public long countFriends_rec(int n) {
        if (n <= 2) {
            return n;
        }
        return (countFriends_rec(n - 1) % mod + ((n - 1) % mod * countFriends_rec(n - 2) % mod));
    }

    // memo
    public long countFriends_memo(int n, long[] dp) {
        if (n <= 2) {
            return dp[n] = n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = (countFriends_memo(n - 1, dp) % mod + ((n - 1) % mod * countFriends_memo(n - 2, dp) % mod))
                % mod;
    }

    // tabu
    public long countFriends_tabu(int N, long[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 2) {
                dp[n] = n;
                continue;
            }
            dp[n] = (dp[n - 1] + (n - 1) % mod * dp[n - 2]) % mod; // (countFriends_memo(n-1,dp)%mod+((n-1)%mod*countFriends_memo(n-2,dp)%mod))%mod;
        }
        return dp[N];
    }

    // optimisation
    public long countFriends_opti(int N) {
        long a = 1, b = 2;
        if (N == 1) {
            return a;
        }
        for (int n = 3; n <= N; n++) {
            long ways = (b + ((n - 1) % mod * a % mod) % mod) % mod;
            a = b;
            b = ways;
        }
        return b;
    }

    public long countFriendsPairings(int n) {
        return countFriends_opti(n);
    }
}
