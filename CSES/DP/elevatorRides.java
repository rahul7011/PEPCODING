import java.util.*;

public class elevatorRides {
    // This is a similar problem to ElevatorRides
    // Leetcode 1986
    class Solution {
        // [3,1,3,1,1], sessionTime = 8
        // This is a classic bitmasking dp problem
        private int SessionTime;
        private int[][] dp;

        private int solve(int[] tasks, int timeLeft, int visited) {
            if (visited == ((1 << tasks.length) - 1)) {
                return dp[timeLeft][visited] = 1;
            }
            if (dp[timeLeft][visited] != 0) {
                return dp[timeLeft][visited];
            }
            int min = (int) 1e9;
            boolean newSlot = true;
            for (int i = 0; i < tasks.length; i++) {
                int mask = (1 << i);
                if ((visited & mask) != 0)
                    continue;
                if (timeLeft - tasks[i] >= 0) {
                    min = Math.min(min, solve(tasks, timeLeft - tasks[i], visited | mask));
                    newSlot = false;
                }
            }
            if (newSlot == true) {
                min = Math.min(min, solve(tasks, SessionTime, visited) + 1);
            }
            return dp[timeLeft][visited] = min;
        }

        public int minSessions(int[] tasks, int sessionTime) {
            SessionTime = sessionTime;
            dp = new int[sessionTime + 1][(1 << tasks.length)];
            return solve(tasks, sessionTime, 0);
        }
    }

    private static int Space;

    private static int solve(int[] arr, int spaceLeft, int visited, int[][] dp) {
        if (visited == ((1 << arr.length) - 1)) {
            return dp[spaceLeft][visited] = 1;
        }
        if (dp[spaceLeft][visited] != 0)
        {
            return dp[spaceLeft][visited];
        }
        boolean newRide = true;
        int min = (int) 1e9;
        for (int i = 0; i < arr.length; i++) {
            int mask = (1 << i);
            if ((visited & mask) != 0) {
                continue;
            }
            if (spaceLeft - arr[i] >= 0) {
                min = Math.min(min, solve(arr, spaceLeft - arr[i], visited | mask, dp));
                newRide = false;
            }
        }
        if (newRide == true) {
            min = Math.min(min, solve(arr, Space, visited, dp) + 1);
        }
        return dp[spaceLeft][visited]=min;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int x = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        Space = x;
        int[][] dp = new int[x + 1][(1 << n)];
        System.out.println(solve(arr, x, 0, dp));
    }
}
