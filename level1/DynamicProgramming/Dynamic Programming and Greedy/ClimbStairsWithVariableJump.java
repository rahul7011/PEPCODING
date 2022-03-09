import java.util.Scanner;

public class ClimbStairsWithVariableJump {
    public static int solve(int[] arr, int n) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= arr[i] && (i + j) <= n; j++) {
                dp[i] += dp[i + j];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        System.out.println(solve(arr, n));
        scn.close();
    }

}
