import java.util.Arrays;
import java.util.Scanner;

public class diceCombinations {
    private static int mod = (int) 1e9 + 7;

    private static int solve(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }
            int count = 0;
            for (int i = 1; i <= 6; i++) {
                if (n - i >= 0)
                    count = (count % mod + dp[n - i] % mod) % mod;
                else
                    break;
            }
            dp[n] = count % mod;
        }
        return dp[N];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] dp = new int[n + 1];
        System.out.println(solve(n, dp));
    }
}