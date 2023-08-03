import java.util.Arrays;
import java.util.Scanner;

public class coinCombinations2 {
    private static int mod = (int) 1e9 + 7;

    private static int solve(int[] arr, int X, int[] dp) {
        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x <= X; x++) {
                if (x == 0) {
                    dp[x] = 1;
                    continue;
                }
                if (x - arr[i] >= 0) {
                    dp[x] = (dp[x] % mod + dp[x - arr[i]] % mod) % mod;
                }
            }
        }
        return dp[X];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int x = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int[] dp = new int[x + 1];
        System.out.println(solve(arr, x, dp));
    }
}
