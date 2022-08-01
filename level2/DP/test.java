import java.util.Arrays;

public class test {
    private static int friendsPairing(int n, String psf, boolean[] visited) {
        int idx = 1;
        while (idx <= n) {
            if (visited[idx] == false) {
                break;
            }
            idx++;
        }
        if (idx > n) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        // single call
        visited[idx] = true;
        count += friendsPairing(n, psf + idx + " ", visited);
        for (int i = idx + 1; i <= n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                count += friendsPairing(n, psf + idx + "" + i + " ", visited);
                visited[i] = false;
            }
        }
        visited[idx] = false;
        return count;
    }

    private static void friendsCall() {
        boolean[] visited = new boolean["ABC".length() + 1];
        friendsPairing(3, "", visited);
    }

    // 115. Distinct Subsequences
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

    private static void distinceSubseqCall() {
        String s1 = "geeksforgeeks";
        String s2 = "gks";
        System.out.println(numDistinct_rec(s1, s2, s1.length(), s2.length()));
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        System.out.println(numDistinct_memo(s1, s2, s1.length(), s2.length(), dp));
    }

    // 10. Regular Expression Matching
    private static boolean regularExpressionMatching_rec(String s1, String s2, int n, int m) {
        if (m <= 0) {
            if (n == 0 && m == 0) {
                return true;
            }
            return false;
        }
        if (n < 0) {
            return false;
        }
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

    private static void regularExpMatchCall() {
        String s1 = "bb";
        String s2 = ".bab";
        // String s1 = "mississippi";
        // String s2 = "mis*is*p*.";
        System.out.println(regularExpressionMatching_rec(s1, s2, s1.length(), s2.length()));
        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];
        System.out.println(regularExpressionMatching_memo(s1, s2, s1.length(), s2.length(), dp));
    }

    public static void main(String[] args) {
        // friendsCall();
        // distinceSubseqCall();
        // regularExpMatchCall();
        System.out.println(Math.floor(Math.sqrt(10)));
    }
}
