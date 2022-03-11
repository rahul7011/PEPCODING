import java.util.Scanner;

public class CoinChangeCombination {
    public static int solve(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (arr[i] <= j) {
                    dp[j] += dp[j - arr[i]];
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