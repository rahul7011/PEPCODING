import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class string_set {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

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

    // 718. Maximum Length of Repeated Subarray
    class Solution0 {
        private static int longestCommonSubstring_tabu(int[] s1, int[] s2, int N, int M, int[][] dp) {
            // ei- ending index
            int maxLen = 0, ei = 0;
            for (int n = 0; n <= N; n++) {
                for (int m = 0; m <= M; m++) {
                    if (n == 0 || m == 0) {
                        dp[n][m] = 0;
                        continue;
                    }
                    if (s1[n - 1] == s2[m - 1]) {
                        dp[n][m] = dp[n - 1][m - 1] + 1; // longestCommonSubsequence(s1, s2, n - 1, m - 1, dp) + 1;
                        if (maxLen < dp[n][m]) {
                            maxLen = dp[n][m];
                            ei = n;
                        }
                    }
                }
            }
            // String longestSubstring=s1.substring(ei-maxLen,ei);
            // System.out.println(longestSubstring);
            return maxLen;
        }

        public int findLength(int[] nums1, int[] nums2) {
            int n = nums1.length, m = nums2.length;
            int[][] dp = new int[n + 1][m + 1];
            return longestCommonSubstring_tabu(nums1, nums2, n, m, dp);
        }
    }

    // 115. Distinct Subsequences(My approach)
    class Solution {
        private static int numDistinct_rec(String s1, String s2, int i, int j) {
            if (i == 0 || j == 0) {
                return j == 0 ? 1 : 0;
            }
            while (i != 0 && s2.charAt(j - 1) != s1.charAt(i - 1)) {
                i--;
            }
            if (i == 0) {
                return 0;
            }
            int notInclude = numDistinct_rec(s1, s2, i - 1, j);
            int include = numDistinct_rec(s1, s2, i - 1, j - 1);
            return notInclude + include;
        }

        private static int numDistinct_memo(String s1, String s2, int i, int j, int[][] dp) {
            if (i == 0 || j == 0) {
                return dp[i][j] = (j == 0 ? 1 : 0);
            }
            while (i != 0 && s2.charAt(j - 1) != s1.charAt(i - 1)) {
                i--;
            }
            if (i == 0) {
                return dp[i][j] = 0;
            }
            if (dp[i][j] != -1) {
                return dp[i][j];
            }
            int notInclude = numDistinct_memo(s1, s2, i - 1, j, dp);
            int include = numDistinct_memo(s1, s2, i - 1, j - 1, dp);
            return dp[i][j] = notInclude + include;
        }

        public int numDistinct(String s, String t) {
            // return numDistinct_rec(s,t,s.length(),t.length());
            int[][] dp = new int[s.length() + 1][t.length() + 1];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            return numDistinct_memo(s, t, s.length(), t.length(), dp);
        }
    }

    // 115. Sir Approach

    public int numDistinct(String s, String t, int n, int m, int[][] dp) {
        if (m == 0) {
            return dp[n][m] = 1;
        }

        if (n < m) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = numDistinct(s, t, n - 1, m - 1, dp);
        int b = numDistinct(s, t, n - 1, m, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = a + b;
        else
            return dp[n][m] = b;
    }

    public int numDistinct_DP(String s, String t, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }

                int a = dp[n - 1][m - 1];// numDistinct(s, t, n - 1, m - 1, dp);
                int b = dp[n - 1][m];// numDistinct(s, t, n - 1, m, dp);

                if (s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = a + b;
                else
                    dp[n][m] = b;
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = numDistinct(s, t, n, m, dp);

        return ans;
    }

    // 72. Edit Distance
    class Solution1 {
        private static int minDistance_rec(String s1, String s2, int n, int m) {
            if (n == 0 || m == 0) {
                return (n == 0 ? m : n);
            }
            int insert = minDistance_rec(s1, s2, n, m - 1);
            int delete = minDistance_rec(s1, s2, n - 1, m);
            int update = minDistance_rec(s1, s2, n - 1, m - 1);
            if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                return update;
            } else {
                return Math.min(insert, Math.min(delete, update)) + 1;
            }
        }

        private static int minDistance_memo(String s1, String s2, int n, int m, int[][] dp) {
            if (n == 0 || m == 0) {
                return dp[n][m] = (n == 0 ? m : n);
            }
            if (dp[n][m] != -1) {
                return dp[n][m];
            }
            int insert = minDistance_memo(s1, s2, n, m - 1, dp);
            int delete = minDistance_memo(s1, s2, n - 1, m, dp);
            int update = minDistance_memo(s1, s2, n - 1, m - 1, dp);
            if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                return dp[n][m] = update;
            } else {
                return dp[n][m] = Math.min(insert, Math.min(delete, update)) + 1;
            }
        }

        private static int minDistance_tabu(String s1, String s2, int N, int M, int[][] dp) {
            for (int n = 0; n <= N; n++) {
                for (int m = 0; m <= M; m++) {
                    if (n == 0 || m == 0) {
                        dp[n][m] = (n == 0 ? m : n);
                        continue;
                    }
                    int insert = minDistance_memo(s1, s2, n, m - 1, dp);
                    int delete = minDistance_memo(s1, s2, n - 1, m, dp);
                    int update = minDistance_memo(s1, s2, n - 1, m - 1, dp);
                    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                        dp[n][m] = update;
                        continue;
                    } else {
                        dp[n][m] = Math.min(insert, Math.min(delete, update)) + 1;
                        continue;
                    }
                }
            }
            return dp[N][M];
        }

        public int minDistance(String word1, String word2) {
            // return minDistance_rec(word1,word2,word1.length(),word2.length());
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            for (int[] d : dp)
                Arrays.fill(d, -1);
            // return minDistance_memo(word1,word2,word1.length(),word2.length(),dp);
            return minDistance_tabu(word1, word2, word1.length(), word2.length(), dp);
        }
    }

    // variance of 72. Edit Distance with cost of insertion,replacement and deletion
    // cost : {insert = a, replace = b, delete = c}
    public int minDistance_02(String word1, String word2, int n, int m, int[] cost, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m * cost[0] : n * cost[2]);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance_02(word1, word2, n, m - 1, cost, dp);
        int delete = minDistance_02(word1, word2, n - 1, m, cost, dp);
        int replace = minDistance_02(word1, word2, n - 1, m - 1, cost, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert + cost[0], delete + cost[2]), replace + cost[1]);
    }

    private static boolean wildCardMatching_rec(String s1, String s2, int n, int m) {
        if (m == 0) {
            if (n == 0) {
                return true;
            }
            return false;
        }
        if (n < 0) {
            return false;
        }
        boolean check1 = false, check2 = false;
        if (n != 0 && ((s1.charAt(n - 1) == s2.charAt(m - 1)) || s2.charAt(m - 1) == '?')) {
            check1 = wildCardMatching_rec(s1, s2, n - 1, m - 1);
        } else {
            if (s2.charAt(m - 1) == '*') {
                check2 = wildCardMatching_rec(s1, s2, n, m - 1) || wildCardMatching_rec(s1, s2, n - 1, m);
            }
        }
        return check1 || check2;
    }

    private static boolean wildCardMatching_memo(String s1, String s2, int n, int m, Boolean[][] dp) {
        if (m == 0) {
            if (n == 0) {
                return dp[n][m] = true;
            }
            return dp[n][m] = false;
        }
        if (n < 0) {
            return false;
        }
        if (dp[n][m] != null) {
            return dp[n][m];
        }
        boolean check1 = false, check2 = false;
        if (n != 0 && ((s1.charAt(n - 1) == s2.charAt(m - 1)) || s2.charAt(m - 1) == '?')) {
            check1 = wildCardMatching_memo(s1, s2, n - 1, m - 1, dp);
        } else {
            if (s2.charAt(m - 1) == '*') {
                check2 = wildCardMatching_memo(s1, s2, n, m - 1, dp) || wildCardMatching_memo(s1, s2, n - 1, m, dp);
            }
        }
        // System.out.println(check1+" "+check2);
        return dp[n][m] = (check1 || check2);
    }

    private static boolean wildCardMatching_tabu(String s1, String s2, int N, int M, Boolean[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    if (n == 0) {
                        dp[n][m] = true;
                        continue;
                    }
                    dp[n][m] = false;
                    continue;
                }
                boolean check1 = false, check2 = false;
                if (n != 0 && ((s1.charAt(n - 1) == s2.charAt(m - 1)) || s2.charAt(m - 1) == '?')) {
                    check1 = dp[n - 1][m - 1]; // wildCardMatching_memo(s1, s2, n - 1, m - 1, dp);
                } else {
                    if (s2.charAt(m - 1) == '*') {
                        // check2 = wildCardMatching_memo(s1, s2, n, m - 1, dp)
                        // || wildCardMatching_memo(s1, s2, n - 1, m, dp);
                        check2 = dp[n][m - 1];
                        if (n != 0) {
                            check2 = check2 || dp[n - 1][m];
                        }
                    }
                }
                // System.out.println(check1+" "+check2);
                dp[n][m] = (check1 || check2);
            }
        }
        return dp[N][M];
    }

    private static void wildCardMatchingCall() {
        String s1 = "dsfdfas";
        String s2 = "*f?s";
        System.out.println(wildCardMatching_rec(s1, s2, s1.length(), s2.length()));
        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];
        // System.out.println(wildCardMatching_memo(s1, s2, s1.length(), s2.length(),
        // dp));
        System.out.println(wildCardMatching_tabu(s1, s2, s1.length(), s2.length(), dp));
    }

    // 10. Regular Expression Matching
    class Solution2 {
        private static boolean regularExpressionMatching_rec(String s1, String s2, int n, int m) {
            // System.out.println(n+" "+m);
            if (m <= 0) {
                if (n == 0 && m == 0) {
                    return true;
                }
                return false;
            }
            if (n < 0) {
                return false;
            }
            // System.out.println(n + " " + m);
            boolean check1 = false, check2 = false;
            if (n != 0 && ((s1.charAt(n - 1) == s2.charAt(m - 1)) || s2.charAt(m - 1) == '.')) {
                check1 = regularExpressionMatching_rec(s1, s2, n - 1, m - 1);
            } else {
                if (s2.charAt(m - 1) == '*' && (s2.charAt(m - 2) == '.')) {
                    check2 = regularExpressionMatching_rec(s1, s2, n, m - 2)
                            || regularExpressionMatching_rec(s1, s2, n - 1, m);
                } else if (n != 0 && (s2.charAt(m - 1) == '*' && (s2.charAt(m - 2) == s1.charAt(n - 1)))) {
                    check2 = regularExpressionMatching_rec(s1, s2, n, m - 2)
                            || regularExpressionMatching_rec(s1, s2, n - 1, m);
                } else {
                    if (s2.charAt(m - 1) == '*')
                        check2 = regularExpressionMatching_rec(s1, s2, n, m - 2);
                }
            }
            // System.out.println(n + " " + m + " " + check1 + " " + check2);
            return check1 || check2;
        }

        private static boolean regularExpressionMatching_memo(String s1, String s2, int n, int m, Boolean[][] dp) {
            // System.out.println(n+" "+m);
            if (m <= 0) {
                if (n == 0 && m == 0) {
                    return dp[n][m] = true;
                }
                return false;
            }
            if (n < 0) {
                return false;
            }
            if (dp[n][m] != null) {
                return dp[n][m];
            }
            // System.out.println(n + " " + m);
            boolean check1 = false, check2 = false;
            if (n != 0 && ((s1.charAt(n - 1) == s2.charAt(m - 1)) || s2.charAt(m - 1) == '.')) {
                check1 = regularExpressionMatching_memo(s1, s2, n - 1, m - 1, dp);
            } else {
                if (s2.charAt(m - 1) == '*' && (s2.charAt(m - 2) == '.')) {
                    check2 = regularExpressionMatching_memo(s1, s2, n, m - 2, dp)
                            || regularExpressionMatching_memo(s1, s2, n - 1, m, dp);
                } else if (n != 0 && (s2.charAt(m - 1) == '*' && (s2.charAt(m - 2) == s1.charAt(n - 1)))) {
                    check2 = regularExpressionMatching_memo(s1, s2, n, m - 2, dp)
                            || regularExpressionMatching_memo(s1, s2, n - 1, m, dp);
                } else {
                    if (s2.charAt(m - 1) == '*')
                        check2 = regularExpressionMatching_memo(s1, s2, n, m - 2, dp);
                }
            }
            // System.out.println(n + " " + m + " " + check1 + " " + check2);
            return dp[n][m] = (check1 || check2);
        }

        public boolean isMatch(String s1, String s2) {
            return regularExpressionMatching_rec(s1, s2, s1.length(), s2.length());
        }
    }

    // 1035. Uncrossed Lines(exactly same as longestCommonSubsequence)
    class Solution3 {
        private static int longestCommonSubsequence(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
            if (n == 0 || m == 0) {
                return dp[n][m] = 0;
            }
            if (dp[n][m] != -1) {
                return dp[n][m];
            }
            if (nums1[n - 1] == nums2[m - 1]) {
                dp[n][m] = longestCommonSubsequence(nums1, nums2, n - 1, m - 1, dp) + 1;
            } else {
                dp[n][m] = Math.max(longestCommonSubsequence(nums1, nums2, n - 1, m, dp),
                        longestCommonSubsequence(nums1, nums2, n, m - 1, dp));
            }
            return dp[n][m];
        }

        private static int longestCommonSubsequence_tabu(int[] nums1, int[] nums2, int N, int M, int[][] dp) {
            for (int n = 0; n <= N; n++) {
                for (int m = 0; m <= M; m++) {
                    if (n == 0 || m == 0) {
                        dp[n][m] = 0;
                        continue;
                    }
                    if (nums1[n - 1] == nums2[m - 1]) {
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

        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            int[][] dp = new int[n + 1][m + 1];
            // for (int[] a : dp) {
            // Arrays.fill(a, -1);
            // }
            // return longestCommonSubsequence(nums1, nums2, n, m, dp);
            return longestCommonSubsequence_tabu(nums1, nums2, n, m, dp);
        }
    }

    // 1458. Max Dot Product of Two Subsequences
    private static int maxDotProduct(int[] nums1, int[] nums2, int n, int m) {
        if (n == 0 || m == 0) {
            return (int) -1e9;
        }
        int leftExclude = maxDotProduct(nums1, nums2, n - 1, m);
        int rightExclude = maxDotProduct(nums1, nums2, n, m - 1);
        int bothInclude = maxDotProduct(nums1, nums2, n - 1, m - 1);
        if (bothInclude == (int) -1e9) {
            bothInclude = nums1[n - 1] * nums2[m - 1];
        } else {
            bothInclude += nums1[n - 1] * nums2[m - 1];
        }
        return Math.max(Math.max(leftExclude, Math.max(rightExclude, bothInclude)), nums1[n - 1] * nums2[m - 1]);
    }

    private static int maxDotProduct_memo(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return (int) -1e9;
        }
        if (dp[n][m] != (int) -1e9 + 7) {
            return dp[n][m];
        }
        int leftExclude = maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
        int rightExclude = maxDotProduct_memo(nums1, nums2, n, m - 1, dp);
        int bothInclude = maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp);
        if (bothInclude == (int) -1e9) {
            bothInclude = nums1[n - 1] * nums2[m - 1];
        } else {
            bothInclude += nums1[n - 1] * nums2[m - 1];
        }
        return dp[n][m] = Math.max(Math.max(leftExclude, Math.max(rightExclude, bothInclude)),
                nums1[n - 1] * nums2[m - 1]);
    }

    private static int maxDotProduct_tabu(int[] nums1, int[] nums2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (int) -1e9;
                    continue;
                }
                int leftExclude = dp[n - 1][m]; // maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
                int rightExclude = dp[n][m - 1]; // maxDotProduct_memo(nums1, nums2, n, m - 1, dp);
                int bothInclude = dp[n - 1][m - 1]; // maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp);
                if (bothInclude == (int) -1e9) {
                    bothInclude = nums1[n - 1] * nums2[m - 1];
                } else {
                    bothInclude += nums1[n - 1] * nums2[m - 1];
                }
                dp[n][m] = Math.max(Math.max(leftExclude, Math.max(rightExclude, bothInclude)),
                        nums1[n - 1] * nums2[m - 1]);
            }
        }
        return dp[N][M];
    }

    private static void maxDotProductCall() {
        int[] nums1 = { 2, 1, -2, 5 };
        int[] nums2 = { 3, 0, -6 };
        // System.out.println(maxDotProduct(nums1, nums2, nums1.length, nums2.length));
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        // for (int[] d : dp) {
        // Arrays.fill(d, (int) -1e9 + 7);
        // }
        // System.out.println(maxDotProduct_memo(nums1, nums2, nums1.length,
        // nums2.length, dp));
        System.out.println(maxDotProduct_tabu(nums1, nums2, nums1.length, nums2.length, dp));
    }

    // 5. Longest Palindromic Substring
    class Solution4 {
        // note:this type of question should be done using tabulation
        public String longestPalindrome(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            // si=starting index
            int si = 0;
            int totalPalindrome = 0;
            int maxLength = 0;
            String longestPalindrome = "";
            for (int gap = 0; gap < n; gap++) {
                for (int i = 0, j = gap; j < n; j++, i++) {
                    if (gap == 0) {
                        dp[i][j] = true;
                    } else if (gap == 1) {
                        dp[i][j] = (s.charAt(i) == s.charAt(j) ? true : false);
                    } else {
                        dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                    }
                    if (dp[i][j] == true) {
                        totalPalindrome++;
                        if (maxLength < (j - i + 1)) {
                            maxLength = j - i + 1;
                            si = i;
                        }
                    }
                }
            }
            longestPalindrome = s.substring(si, si + maxLength);
            // System.out.println(totalPalindrome+" "+maxLength+" "+longestPalindrome);
            return longestPalindrome;
        }
    }

    // Longest Common Substring
    // https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1
    class Solution5 {
        private static int longestCommonSubstring_tabu(String s1, String s2, int N, int M, int[][] dp) {
            // ei- ending index
            int maxLen = 0, ei = 0;
            for (int n = 0; n <= N; n++) {
                for (int m = 0; m <= M; m++) {
                    if (n == 0 || m == 0) {
                        dp[n][m] = 0;
                        continue;
                    }
                    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                        dp[n][m] = dp[n - 1][m - 1] + 1; // longestCommonSubsequence(s1, s2, n - 1, m - 1, dp) + 1;
                        if (maxLen < dp[n][m]) {
                            maxLen = dp[n][m];
                            ei = n;
                        }
                    }
                }
            }
            String longestSubstring = s1.substring(ei - maxLen, ei);
            // System.out.println(longestSubstring);
            return maxLen;
        }

        int longestCommonSubstr(String S1, String S2, int n, int m) {
            int[][] dp = new int[n + 1][m + 1];
            return longestCommonSubstring_tabu(S1, S2, n, m, dp);
        }
    }

    // 583. Delete Operation for Two Strings
    class Solution6 {
        // logic: find longestCommonSubseq and subtract twice of it from the sum of
        // s1+s2 lengths
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

        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            for (int[] a : dp) {
                Arrays.fill(a, -1);
            }
            return (word1.length() + word2.length()
                    - 2 * longestCommonSubsequence(word1, word2, word1.length(), word2.length(), dp));
        }
    }

    // 132. Palindrome Partitioning II
    class Solution7 {
        public void longestPalindrome(String s, boolean[][] dp) {
            int si = 0;
            int n = s.length();
            for (int gap = 0; gap < n; gap++) {
                for (int i = 0, j = gap; j < n; j++, i++) {
                    if (gap == 0) {
                        dp[i][j] = true;
                    } else if (gap == 1) {
                        dp[i][j] = (s.charAt(i) == s.charAt(j) ? true : false);
                    } else {
                        dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                    }
                }
            }
        }

        private static int minCut(String s, int si, int ei, int[] dp, boolean[][] palindromeDp) {
            if (palindromeDp[si][ei] == true) {
                // already a palindrome,means no need for cut
                return 0;
            }
            if (dp[si] != -1) {
                return dp[si];
            }
            int minAns = (int) 1e8;
            for (int cut = si; cut <= ei; cut++) {
                if (palindromeDp[si][cut] == true) {
                    minAns = Math.min(minAns, minCut(s, cut + 1, ei, dp, palindromeDp) + 1);
                }
            }
            return dp[si] = minAns;
        }

        public int minCut(String s) {
            int n = s.length();
            boolean[][] palindromeDp = new boolean[n][n];
            // pre-processing part for the O(1) checking of palindrome
            longestPalindrome(s, palindromeDp);
            int[] dp = new int[s.length() + 1];
            Arrays.fill(dp, -1);
            return minCut(s, 0, s.length() - 1, dp, palindromeDp);

        }
    }

    // Count subsequences of type a^i, b^j, c^k
    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    class Solution8 {
        public int fun(String s) {
            long emptyCount = 1, aCount = 0, bCount = 0, cCount = 0, mod = (int) 1e9 + 7;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == 'a') {
                    aCount = aCount + (emptyCount + aCount) % mod;
                } else if (ch == 'b') {
                    bCount = bCount + (aCount + bCount) % mod;
                } else {
                    // it is 'c'
                    cCount = cCount + (bCount + cCount) % mod;
                }
            }
            return (int) (cCount % mod);
        }
    }

    // follow Up Question: ai-bj-ck-dl-em-fn
    public static long countSubsequences_variance(String s) {
        // type : ai-bj-ck-dl-em-fn
        long[] iCount = new long[6];
        long emptyCount = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int pos = ch - 'a';
            iCount[pos] = iCount[pos] + ((pos != 0 ? iCount[pos - 1] : emptyCount) + iCount[pos]);
        }

        return iCount[iCount.length - 1];
    }

    // leetcode 139
    class Solution9 {
        public boolean wordBreak(String s, List<String> wordDict) {
            HashSet<String> hs = new HashSet<>();
            int n = s.length();
            int max = 0;
            for (String x : wordDict) {
                max = Math.max(max, x.length());
                hs.add(x);
            }
            // start forming dp
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 0; i <= n; i++) {
                if (dp[i] == false) {
                    continue;
                }
                for (int l = 1; l <= max && (i + l) <= n; l++) {
                    String x = s.substring(i, i + l);
                    // System.out.println(i+" "+x);
                    if (hs.contains(x) == true) {
                        dp[i + l] = true;
                    }
                }
            }
            return dp[n];
        }
    }

    private static String longestPalindromicSubseq_BackEngine(String s, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return si == ei ? s.charAt(si) + "" : "";
        }
        if (s.charAt(si) == s.charAt(ei)) {
            return s.charAt(si) + longestPalindromicSubseq_BackEngine(s, si + 1, ei - 1, dp) + s.charAt(si);
        } else if (dp[si + 1][ei] > dp[si][ei - 1]) {
            return longestPalindromicSubseq_BackEngine(s, si + 1, ei, dp);
        } else {
            return longestPalindromicSubseq_BackEngine(s, si, ei - 1, dp);
        }
    }

    // 140. Word Break II
    public static List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> hs = new HashSet<>();
        int n = s.length();
        int max = 0;
        for (String x : wordDict) {
            max = Math.max(max, x.length());
            hs.add(x);
        }
        // start forming dp
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (dp[i] == false) {
                continue;
            }
            for (int l = 1; l <= max && (i + l) <= n; l++) {
                String x = s.substring(i, i + l);
                // System.out.println(i+" "+x);
                if (hs.contains(x) == true) {
                    dp[i + l] = true;
                }
            }
        }
        if (dp[n] == true) {
            List<String> ans = new ArrayList<>();
            wordBreak_backEngine(s, 0, s.length(), max, dp, "", ans, hs);
            return ans;
        } else
            return new ArrayList<>();
    }

    private static void wordBreak_backEngine(String s, int si, int ei, int maxLen, boolean[] dp, String psf,
            List<String> ans, HashSet<String> hs) {
        if (si >= ei) {
            // we need to trim the psf,as it includes an extra space in the end
            ans.add(psf.substring(0, psf.length() - 1));
            return;
        }
        for (int l = 1; l <= maxLen && (si + l) <= s.length(); l++) {
            if (dp[l + si] == true) {
                String subStr = s.substring(si, si + l);
                if (hs.contains(subStr) == true) {
                    wordBreak_backEngine(s, si + l, ei, maxLen, dp, psf + subStr + " ", ans, hs);
                }
            }
        }
    }

    private static void backEngineCall() {
        // String s = "ABCBBAD";
        // int[][] dp = new int[s.length()][s.length()];
        // longestPalindromeSubseq_tabu(s, 0, s.length() - 1, dp);
        // System.out.println(longestPalindromicSubseq_BackEngine(s, 0, s.length() - 1,
        // dp));

        String s = "catsanddog";
        List<String> words = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println(wordBreak(s, words));
    }

    // 1278. Palindrome Partitioning III
    class Solution10 {
        public void minChanges_(String s, int[][] dp) {
            int si = 0;
            int n = s.length();
            for (int gap = 0; gap < n; gap++) {
                for (int i = 0, j = gap; j < n; j++, i++) {
                    if (gap == 0) {
                        dp[i][j] = 0;
                    } else if (gap == 1) {
                        dp[i][j] = (s.charAt(i) == s.charAt(j) ? 0 : 1);
                    } else {
                        dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] : dp[i + 1][j - 1] + 1;
                    }
                }
            }
        }

        private static int minCut(String s, int si, int k, int[][] dp, int[][] minChanges) {
            if (s.length() - si <= k) {
                return dp[si][k] = s.length() - si == k ? 0 : (int) 1e8;
            }
            if (k == 1) {
                return dp[si][k] = minChanges[si][s.length() - 1];
            }
            if (dp[si][k] != -1) {
                return dp[si][k];
            }
            int minAns = (int) 1e8;
            for (int cut = si; cut < s.length(); cut++) {
                int minChangesInMyString = minChanges[si][cut];
                int minChangesInRecString = minCut(s, cut + 1, k - 1, dp, minChanges);

                if (minChangesInRecString != (int) 1e8)
                    minAns = Math.min(minAns, minChangesInMyString + minChangesInRecString);
            }
            return dp[si][k] = minAns;
        }

        public int palindromePartition(String s, int k) {
            int n = s.length();
            int[][] minChanges = new int[n][n];
            // pre-processing part for the O(1) finding of min-palindrome changes
            minChanges_(s, minChanges);
            int[][] dp = new int[s.length() + 1][k + 1];
            for (int[] d : dp)
                Arrays.fill(d, -1);
            return minCut(s, 0, k, dp, minChanges);
        }
    }

    // 198. House Robber
    class Solution11 {
        public static int solve(int[] nums, int idx, int[] dp) {
            if (idx == nums.length) {
                return dp[idx] = 0;
            }
            if (dp[idx] != -1) {
                return dp[idx];
            }
            int a1 = nums[idx], a2 = 0;
            if (idx + 2 <= nums.length) {
                a1 = nums[idx] + solve(nums, idx + 2, dp);
            }
            a2 = solve(nums, idx + 1, dp);
            return dp[idx] = Math.max(a1, a2);
        }

        public int rob(int[] nums) {
            int[] dp = new int[nums.length + 1];
            Arrays.fill(dp, -1);
            return solve(nums, 0, dp);
        }
    }

    // 213. House Robber II
    class Solution12 {
        private static int rob(int[] nums, int idx, boolean flag, int[][] dp) {
            if (idx >= nums.length) {
                if (idx == nums.length)
                    return dp[idx][flag == true ? 1 : 0] = 0;
                return 0;
            }
            if (dp[idx][flag == true ? 1 : 0] != -1) {
                return dp[idx][flag == true ? 1 : 0];
            }
            int a1 = 0, a2 = 0, a3 = 0, a4 = 0;
            if (idx == 0) {
                a1 = nums[idx] + rob(nums, idx + 2, true, dp);
            } else if (idx == nums.length - 1) {
                int temp = rob(nums, idx + 1, flag, dp);
                a2 = (flag == false ? nums[idx] : 0) + temp;
            } else {
                a3 = nums[idx] + rob(nums, idx + 2, flag, dp);
            }
            a4 = rob(nums, idx + 1, flag, dp);
            return dp[idx][flag == true ? 1 : 0] = Math.max(a1, Math.max(a2, Math.max(a3, a4)));
        }

        public int rob(int[] nums) {
            int[][] dp = new int[nums.length + 1][2];
            for (int[] d : dp)
                Arrays.fill(d, -1);
            return rob(nums, 0, false, dp);
        }
    }

    // 337. House Robber III
    class Solution13 {
        private int rob(TreeNode root, boolean check, HashMap<String, Integer> hm) {
            if (root == null) {
                return 0;
            }
            // forming key using root and check
            String key = root + "-@-" + check;
            if (hm.containsKey(key) == true) {
                return hm.get(key);
            }
            int a1 = 0, a2 = 0;
            if (check == true) {
                a1 = root.val + rob(root.left, false, hm) + rob(root.right, false, hm);
            }
            a2 = rob(root.left, true, hm) + rob(root.right, true, hm);
            hm.put(key, Math.max(a1, a2));
            return hm.get(key);
        }

        public int rob(TreeNode root) {
            HashMap<String, Integer> hm = new HashMap<>();
            return rob(root, true, hm);
        }
    }

    // Similar to House Robber 2
    // 1388. Pizza With 3n Slices
    class Solution14 {
        private int maxSizeSlices(int[] slices, int si, int ei, int NoOfSlices, int[][] dp) {
            if (si > ei || NoOfSlices == 0) {
                return 0;
            }
            if (dp[ei][NoOfSlices] != -1) {
                return dp[ei][NoOfSlices];
            }
            int takeThisSlice = slices[ei] + maxSizeSlices(slices, si, ei - 2, NoOfSlices - 1, dp);
            int leaveThisSlice = maxSizeSlices(slices, si, ei - 1, NoOfSlices, dp);
            return dp[ei][NoOfSlices] = Math.max(takeThisSlice, leaveThisSlice);
        }

        public int maxSizeSlices(int[] slices) {
            int n = slices.length;
            int[][] dp1 = new int[n + 1][n / 3 + 1];
            int[][] dp2 = new int[n + 1][n / 3 + 1];
            for (int[] d : dp1)
                Arrays.fill(d, -1);
            for (int[] d : dp2)
                Arrays.fill(d, -1);
            return Math.max(maxSizeSlices(slices, 0, n - 2, n / 3, dp1), maxSizeSlices(slices, 1, n - 1, n / 3, dp2));
        }
    }

    public static void main(String[] args) {
        // palindromicSubseqCall();
        // commonSubseqCall();
        // wildCardMatchingCall();
        // maxDotProductCall();
        // System.out.println(countSubsequences_variance("abcdef"));

        backEngineCall();

    }
}
