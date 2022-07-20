import java.util.Arrays;

public class string_set {
    // 516. Longest Palindromic Subsequence
    private static int longestPalindromeSubseq_rec(String s, int si, int sj) {
        if (si == sj) {
            return 1;
        }
        if (si > sj) {
            return 0;
        }
        int bothInclude = 0, partInclude = 0;
        if (s.charAt(si) == s.charAt(sj)) {
            bothInclude = longestPalindromeSubseq_rec(s, si + 1, sj - 1) + 2;
        } else {
            partInclude = Math.max(longestPalindromeSubseq_rec(s, si + 1, sj),
                    longestPalindromeSubseq_rec(s, si, sj - 1));
        }
        return Math.max(bothInclude, partInclude);
    }

    private static int longestPalindromeSubseq_memo(String s, int si, int sj, int[][] dp) {
        if (si == sj) {
            return dp[si][sj] = 1;
        }
        if (dp[si][sj] != 0) {
            return dp[si][sj];
        }
        if (si > sj) {
            return 0;
        }
        int bothInclude = 0, partInclude = 0;
        if (s.charAt(si) == s.charAt(sj)) {
            bothInclude = longestPalindromeSubseq_memo(s, si + 1, sj - 1, dp) + 2;
        } else {
            partInclude = Math.max(longestPalindromeSubseq_memo(s, si + 1, sj, dp),
                    longestPalindromeSubseq_memo(s, si, sj - 1, dp));
        }
        return dp[si][sj] = Math.max(bothInclude, partInclude);
    }

    // Note: for tabulation in this case we use GAP strategy which is basically
    // filling blocks diagonally
    private static int longestPalindromeSubseq_tabu(String s, int SI, int SJ, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, sj = gap; sj < n; si++, sj++) {
                if (si == sj) {
                    dp[si][sj] = 1;
                    continue;
                }
                int bothInclude = 0, partInclude = 0;
                if (s.charAt(si) == s.charAt(sj)) {
                    bothInclude = dp[si + 1][sj - 1] + 2; // longestPalindromeSubseq_memo(s, si + 1, sj - 1, dp) + 2;
                } else {
                    // partInclude = Math.max(longestPalindromeSubseq_memo(s, si + 1, sj, dp),
                    // longestPalindromeSubseq_memo(s, si, sj - 1, dp));
                    partInclude = Math.max(dp[si + 1][sj],
                            dp[si][sj - 1]);
                }
                dp[si][sj] = Math.max(bothInclude, partInclude);
            }
        }
        return dp[SI][SJ];
    }

    private static void palindromicSubseqCall() {
        String s = "geekske";
        // System.out.println(longestPalindromeSubseq_rec(s, 0, s.length() - 1));
        int[][] dp = new int[s.length()][s.length()];
        // System.out.println(longestPalindromeSubseq_memo(s, 0, s.length() - 1, dp));
        // display2D(dp);
        System.out.print(longestPalindromeSubseq_tabu(s, 0, s.length() - 1, dp));
    }

    // 1143. Longest Common Subsequence
    private static int longestCommonSubsequence(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            dp[n][m] = longestCommonSubsequence(s1, s2, n - 1, m - 1, dp) + 1;
        } else {
            dp[n][m] = Math.max(longestCommonSubsequence(s1, s2, n - 1, m, dp),
                    longestCommonSubsequence(s1, s2, n, m - 1, dp));
        }
        return dp[n][m];
    }

    private static int longestCommonSubsequence_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }
                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + 1; // longestCommonSubsequence(s1, s2, n - 1, m - 1, dp) + 1;
                } else {
                    // dp[n][m] = Math.max(longestCommonSubsequence(s1, s2, n - 1, m, dp),
                    // longestCommonSubsequence(s1, s2, n, m - 1, dp));
                    dp[n][m] = Math.max(dp[n - 1][m],
                            dp[n][m - 1]);
                }
            }
        }
        return dp[N][M];
    }

    private static void commonSubseqCall() {
        String s1 = "abcde";
        String s2 = "ace";
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        // System.out.println(longestCommonSubsequence(s1, s2, n, m, dp));
        System.out.println(longestCommonSubsequence_tabu(s1, s2, n, m, dp));
    }

    public static void main(String[] args) {
        // palindromicSubseqCall();
        commonSubseqCall();
    }
}
