public class two_pointer {
    private static void display(int[] dp) {
        for (int x : dp) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    private static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    // 1 faith
    // 2 Recursive Tree
    // 3 Recursive Code->Memoization
    // 4 Observation
    // 5 Tabulation
    // 6 Optimisation

    private static int fib_rec(int n) {
        if (n <= 1) {
            return n;
        }
        int ans = fib_rec(n - 1) + fib_rec(n - 2);
        return ans;
    }

    private static int fib_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int ans = fib_memo(n - 1, dp) + fib_memo(n - 2, dp);
        return dp[n] = ans;
    }

    private static int fib_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }
            int ans = dp[n - 1] + dp[n - 2]; // fib_tabu(n-1,dp)+fib_tabu(n-2,dp);
            dp[n] = ans;
        }
        return dp[N];
    }

    private static int fib_opti(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int c = a + b;
            // System.out.print(a+" ");
            a = b;
            b = c;
        }
        return a;
    }

    private static void fibCall() {
        int n = 9;
        // System.out.println(fib_rec(n));
        int[] dp = new int[n + 1];
        // fib_memo(n, dp);
        // fib_tabu(n, dp);
        // display(dp);
        fib_opti(n);

    }

    private static int maze_rec(int sr, int sc, int er, int ec, int[][] dir) {
        if (sr == er && sc == ec) {
            return 1;
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if (x >= 0 && y >= 0 && x <= er && y <= ec) {
                count += maze_rec(x, y, er, ec, dir);
            }
        }
        return count;
    }

    private static int maze_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if (x >= 0 && y >= 0 && x <= er && y <= ec) {
                count += maze_memo(x, y, er, ec, dir, dp);
            }
        }
        return dp[sr][sc] = count;
    }

    private static int maze_tabu(int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
        for (int sr = ER; sr >= 0; sr--) {
            for (int sc = EC; sc >= 0; sc--) {
                if (sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int x = sr + dir[d][0];
                    int y = sc + dir[d][1];
                    if (x >= 0 && y >= 0 && x <= ER && y <= EC) {
                        count += dp[x][y]; // maze_memo(x, y, er, ec, dir, dp);
                    }
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    private static int mazeJump_rec(int sr, int sc, int er, int ec, int[][] dir) {
        if (sr == er && sc == ec) {
            return 1;
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int x = sr + rad * dir[d][0];
                int y = sc + rad * dir[d][1];
                if (x >= 0 && y >= 0 && x <= er && y <= ec) {
                    count += mazeJump_rec(x, y, er, ec, dir);
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private static int mazeJump_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int x = sr + rad * dir[d][0];
                int y = sc + rad * dir[d][1];
                if (x >= 0 && y >= 0 && x <= er && y <= ec) {
                    count += mazeJump_memo(x, y, er, ec, dir, dp);
                } else {
                    break;
                }
            }
        }
        return dp[sr][sc] = count;
    }

    private static int mazeJump_tabu(int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
        for (int sr = ER; sr >= 0; sr--) {
            for (int sc = EC; sc >= 0; sc--) {
                if (sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    // for (int rad = 1; rad <= Math.max(ER, EC); rad++) {
                    // int x = sr + rad * dir[d][0];
                    // int y = sc + rad * dir[d][1];
                    // if (x >= 0 && y >= 0 && x <= ER && y <= EC) {
                    // count += dp[x][y]; //mazeJump_memo(x, y, er, ec, dir, dp);
                    // } else {
                    // break;
                    // }
                    // }

                    // ==========Above Radius loop can also be written as--> ==============//

                    int x = sr + dir[d][0];
                    int y = sc + dir[d][1];

                    while (x >= 0 && y >= 0 && x <= ER && y <= EC) {
                        count += dp[x][y]; // mazeJump_memo(x, y, er, ec, dir, dp);
                        x += dir[d][0];
                        y += dir[d][1];
                    }

                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    private static void mazeCall() {
        int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
        int n = 3, m = 3;
        // System.out.println(maze_rec(0, 0, n-1, m-1, dir));
        int[][] dp = new int[n][m];
        System.out.println(maze_memo(0, 0, n - 1, m - 1, dir, dp));
        System.out.println(maze_tabu(0, 0, n - 1, m - 1, dir, dp));

        // System.out.println(mazeJump_rec(0, 0, n-1, m-1, dir));
        // System.out.println(mazeJump_memo(0, 0, n - 1, m - 1, dir, dp));
        // System.out.println(mazeJump_tabu(0, 0, n - 1, m - 1, dir, dp));
    }

    // Unique path 62
    class Solution {
        private static int maze_rec(int sr, int sc, int er, int ec, int[][] dir) {
            if (sr == er && sc == ec) {
                return 1;
            }
            int count = 0;
            for (int d = 0; d < dir.length; d++) {
                int x = sr + dir[d][0];
                int y = sc + dir[d][1];
                if (x >= 0 && y >= 0 && x <= er && y <= ec) {
                    count += maze_rec(x, y, er, ec, dir);
                }
            }
            return count;
        }

        private static int maze_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
            if (sr == er && sc == ec) {
                return dp[sr][sc] = 1;
            }
            if (dp[sr][sc] != 0) {
                return dp[sr][sc];
            }
            int count = 0;
            for (int d = 0; d < dir.length; d++) {
                int x = sr + dir[d][0];
                int y = sc + dir[d][1];
                if (x >= 0 && y >= 0 && x <= er && y <= ec) {
                    count += maze_memo(x, y, er, ec, dir, dp);
                }
            }
            return dp[sr][sc] = count;
        }

        private static int maze_tabu(int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
            for (int sr = ER; sr >= 0; sr--) {
                for (int sc = EC; sc >= 0; sc--) {
                    if (sr == ER && sc == EC) {
                        dp[sr][sc] = 1;
                        continue;
                    }
                    int count = 0;
                    for (int d = 0; d < dir.length; d++) {
                        int x = sr + dir[d][0];
                        int y = sc + dir[d][1];
                        if (x >= 0 && y >= 0 && x <= ER && y <= EC) {
                            count += dp[x][y]; // maze_memo(x, y, er, ec, dir, dp);
                        }
                    }
                    dp[sr][sc] = count;
                }
            }
            return dp[SR][SC];
        }

        public int uniquePaths(int m, int n) {
            int[][] dir = { { 0, 1 }, { 1, 0 } };
            // return maze_rec(0,0,m-1,n-1,dir);
            int[][] dp = new int[m][n];
            // return maze_memo(0,0,m-1,n-1,dir,dp);
            return maze_tabu(0, 0, m - 1, n - 1, dir, dp);
        }
    }

    // unique path 2
    class Solution1 {
        private static int maze_rec(int[][] obstacleGrid, int sr, int sc, int er, int ec, int[][] dir) {
            if (sr == er && sc == ec) {
                return 1;
            }
            int count = 0;
            for (int d = 0; d < dir.length; d++) {
                int x = sr + dir[d][0];
                int y = sc + dir[d][1];
                if (x >= 0 && y >= 0 && x <= er && y <= ec && obstacleGrid[x][y] != 1) {
                    count += maze_rec(obstacleGrid, x, y, er, ec, dir);
                }
            }
            return count;
        }

        private static int maze_memo(int[][] obstacleGrid, int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
            if (sr == er && sc == ec) {
                return dp[sr][sc] = 1;
            }
            if (dp[sr][sc] != 0) {
                return dp[sr][sc];
            }
            int count = 0;
            for (int d = 0; d < dir.length; d++) {
                int x = sr + dir[d][0];
                int y = sc + dir[d][1];
                if (x >= 0 && y >= 0 && x <= er && y <= ec && obstacleGrid[x][y] != 1) {
                    count += maze_memo(obstacleGrid, x, y, er, ec, dir, dp);
                }
            }
            return dp[sr][sc] = count;
        }

        private static int maze_tabu(int[][] obstacleGrid, int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
            for (int sr = ER; sr >= 0; sr--) {
                for (int sc = EC; sc >= 0; sc--) {
                    if (sr == ER && sc == EC) {
                        dp[sr][sc] = 1;
                        continue;
                    }
                    int count = 0;
                    for (int d = 0; d < dir.length; d++) {
                        int x = sr + dir[d][0];
                        int y = sc + dir[d][1];
                        if (x >= 0 && y >= 0 && x <= ER && y <= EC && obstacleGrid[x][y] != 1) {
                            count += dp[x][y]; // maze_memo(x, y, er, ec, dir, dp);
                        }
                    }
                    dp[sr][sc] = count;
                }
            }
            return dp[SR][SC];
        }

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length, n = obstacleGrid[0].length;
            int[][] dir = { { 0, 1 }, { 1, 0 } };
            if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
                return 0;
            }
            // return maze_rec(obstacleGrid,0,0,m-1,n-1,dir);
            int[][] dp = new int[m][n];
            // return maze_memo(obstacleGrid,0,0,m-1,n-1,dir,dp);
            return maze_tabu(obstacleGrid, 0, 0, m - 1, n - 1, dir, dp);
        }
    }

    // Climbing Stairs 70
    class Solution2 {
        private static int fib_rec(int n) {
            if (n <= 1) {
                return 1;
            }
            int ans = fib_rec(n - 1) + fib_rec(n - 2);
            return ans;
        }

        private static int fib_memo(int n, int[] dp) {
            if (n <= 1) {
                return dp[n] = 1;
            }
            if (dp[n] != 0) {
                return dp[n];
            }
            int ans = fib_memo(n - 1, dp) + fib_memo(n - 2, dp);
            return dp[n] = ans;
        }

        private static int fib_tabu(int N, int[] dp) {
            for (int n = 0; n <= N; n++) {
                if (n <= 1) {
                    dp[n] = 1;
                    continue;
                }
                int ans = dp[n - 1] + dp[n - 2]; // fib_tabu(n-1,dp)+fib_tabu(n-2,dp);
                dp[n] = ans;
            }
            return dp[N];
        }

        private static int fib_opti(int n) {
            int a = 1, b = 1;
            for (int i = 0; i < n; i++) {
                int c = a + b;
                // System.out.print(a+" ");
                a = b;
                b = c;
            }
            return a;
        }

        public int climbStairs(int n) {
            // return fib_rec(n);
            // int[] dp=new int[n+1];
            // return fib_memo(n,dp);
            // return fib_tabu(n,dp);
            return fib_opti(n);
        }
    }

    // 746. Min Cost Climbing Stairs
    class Solution3 {
        private static int minCost_rec(int[] cost, int idx) {
            if (idx < 2) {
                return cost[idx];
            }
            return Math.min(minCost_rec(cost, idx - 1), minCost_rec(cost, idx - 2))
                    + (idx == cost.length ? 0 : cost[idx]);
        }

        private static int minCost_memo(int[] cost, int idx, int[] dp) {
            if (idx < 2) {
                return dp[idx] = cost[idx];
            }
            if (dp[idx] != 0) {
                return dp[idx];
            }
            return dp[idx] = Math.min(minCost_memo(cost, idx - 1, dp), minCost_memo(cost, idx - 2, dp))
                    + (idx == cost.length ? 0 : cost[idx]);
        }

        private static int minCost_tabu(int[] cost, int IDX, int[] dp) {
            for (int idx = 0; idx <= IDX; idx++) {
                if (idx < 2) {
                    dp[idx] = cost[idx];
                    continue;
                }
                dp[idx] = Math.min(dp[idx - 1], dp[idx - 2]) + (idx == cost.length ? 0 : cost[idx]);
            }
            return dp[IDX];
        }

        public int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length + 1];
            // return minCost_rec(cost,cost.length);
            // return minCost_memo(cost,cost.length,dp);
            return minCost_tabu(cost, cost.length, dp);
        }
    }

    private static int boardDice_rec(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 0;
        for (int dice = 1; dice <= 6; dice++) {
            if (n - dice >= 0) {
                count += boardDice_rec(n - dice);
            }
        }
        return count;
    }

    private static int boardDice_memo(int n, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int count = 0;
        for (int dice = 1; dice <= 6; dice++) {
            if (n - dice >= 0) {
                count += boardDice_memo(n - dice, dp);
            }
        }
        return dp[n] = count;
    }

    private static int boardDice_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }
            int count = 0;
            for (int dice = 1; dice <= 6; dice++) {
                if (n - dice >= 0) {
                    count += dp[n - dice]; // boardDice_memo(n-dice,dp);
                }
            }
            dp[n] = count;
        }
        return dp[N];
    }

    private static void boardCall() {
        int n = 3;
        // System.out.println(boardDice_rec(n));
        int[] dp = new int[n + 1];
        // System.out.println(boardDice_memo(n, dp));
        // display(dp);
        System.out.println(boardDice_tabu(n, dp));
    }

    public static void main(String[] args) {
        // fibCall();
        // mazeCall();
        boardCall();
    }
}
