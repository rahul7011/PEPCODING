import java.util.Scanner;

public class CoinChangePermutations {
    public static int solve(int[] arr, int target) {
        int[] dp = new int[target + 1];
        int n = arr.length;
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j] <= i) {
                    dp[i] += dp[i - arr[j]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int target = scn.nextInt();
        System.out.println(solve(arr, target));
        scn.close();
    }
}