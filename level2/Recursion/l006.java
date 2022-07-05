import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class l006 {
    // Sudoku solver

    // https://leetcode.com/problems/sudoku-solver/

    // Not optimised
    public static boolean isSafeToPlaceNumber(char[][] board, int r, int c, int num) {
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < 10; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                    if (board[x][y] - '0' == num) {
                        return false;
                    }
                }
            }
        }
        // now checking the specific block
        int br = (r / 3) * 3;
        int bc = (c / 3) * 3;
        for (int i = br; i < br + 3; i++) {
            for (int j = bc; j < bc + 3; j++) {
                if (board[i][j] - '0' == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solveSudoku(char[][] board, ArrayList<Integer> emptyIndex, int idx) {
        if (idx == emptyIndex.size()) {
            return true;
        }

        int cell = emptyIndex.get(idx);
        int r = cell / 9;
        int c = cell % 9;
        for (int num = 1; num < 10; num++) {
            if (isSafeToPlaceNumber(board, r, c, num) == true) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, emptyIndex, idx + 1) == true) {
                    // checking in if,just to retreive a single answer
                    return true;
                }
                board[r][c] = '.';
            }
        }
        return false;
    }

    public void solveSudoku1(char[][] board) {
        ArrayList<Integer> emptyIndex = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // for converting 2d to 1d using (r*m + c)
                    emptyIndex.add(i * 9 + j);
                }
            }
        }
        solveSudoku(board, emptyIndex, 0);
    }

    // Optimised 1 using bits
    int[] row = new int[9];
    int[] col = new int[9];
    int[][] mat = new int[3][3];

    private boolean solveSudoku2(char[][] board, ArrayList<Integer> emptyIndex, int idx) {
        if (idx == emptyIndex.size()) {
            return true;
        }

        int cell = emptyIndex.get(idx);
        int r = cell / 9;
        int c = cell % 9;
        for (int num = 1; num < 10; num++) {
            int mask = (1 << num);
            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
                board[r][c] = (char) (num + '0');
                if (solveSudoku2(board, emptyIndex, idx + 1) == true) {
                    // checking in if,just to retreive a single answer
                    return true;
                }
                board[r][c] = '.';
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> emptyIndex = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // for converting 2d to 1d using (r*m + c)
                    emptyIndex.add(i * 9 + j);
                } else {
                    int mask = (1 << board[i][j] - '0');
                    row[i] ^= mask;
                    col[j] ^= mask;
                    mat[i / 3][j / 3] ^= mask;
                }
            }
        }
        solveSudoku2(board, emptyIndex, 0);
    }

    // https://leetcode.com/problems/valid-sudoku/
    class Solution {
        // Most Optimised used bits
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] mat = new int[3][3];

        public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int mask = (1 << board[i][j] - '0');
                        if ((row[i] & mask) == 0 && (col[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {
                            row[i] ^= mask;
                            col[j] ^= mask;
                            mat[i / 3][j / 3] ^= mask;
                        } else {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    // https://practice.geeksforgeeks.org/problems/word-break1352/1/#
    class Sol {
        private static int wordBreak(String s, int maxl, HashSet<String> hs, String ans) {
            if (s.length() == 0) {
                return 1;
            }
            int count = 0;
            for (int i = 0; i < Math.min(s.length(), maxl); i++) {
                String check = s.substring(0, i + 1);
                if (hs.contains(check) == true) {
                    count += wordBreak(s.substring(i + 1), maxl, hs, ans + check + " ");
                }
            }
            return count;
        }

        public static int wordBreak(String s, ArrayList<String> B) {
            HashSet<String> hs = new HashSet<>();
            int maxl = 0;
            for (String x : B) {
                maxl = Math.max(x.length(), maxl);
                hs.add(x);
            }
            return wordBreak(s, maxl, hs, "");
        }
    }

    class Solution1 {
        // https://leetcode.com/problems/word-break/
        private boolean wordBreak(String s, int maxl, HashSet<String> hs, int idx) {
            if (idx == s.length()) {
                return true;
            }
            String check = "";
            for (int i = idx; i < Math.min(s.length(), maxl + idx); i++) {
                check += s.charAt(i);
                if (hs.contains(check) == true) {
                    if (wordBreak(s, maxl, hs, i + 1) == true) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean wordBreak(String s, List<String> wordDict) {
            HashSet<String> hs = new HashSet<>();
            int maxl = 0;
            for (String x : wordDict) {
                maxl = Math.max(x.length(), maxl);
                hs.add(x);
            }
            return wordBreak(s, maxl, hs, 0);
        }
    }

    // friends Pairing string version(My approach)
    public static int friendPairingString(String s, String ans) {
        if (s.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char ch = s.charAt(0);
        count += friendPairingString(s.substring(1), ans + ch + " ");
        for (int i = 1; i < s.length(); i++) {
            String str = ch + "" + s.charAt(i);
            count += friendPairingString(s.substring(1, i) + s.substring(i + 1), ans + str + " ");
        }
        return count;
    }

    // friends Pairing string version(Sir approach)
    public static int friendPairingString1(String s, String ans, boolean[] used) {
        int idx = 0;
        while (idx < used.length) {
            if (used[idx] == false) {
                break;
            }
            idx++;
        }
        if (idx >= used.length) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char ch = s.charAt(idx);
        used[idx] = true;
        count += friendPairingString1(s, ans + "(" + ch + ") ", used);
        for (int i = idx + 1; i < s.length(); i++) {
            if (used[i] == false) {
                String str = ch + "" + s.charAt(i);
                used[i] = true;
                count += friendPairingString1(s, ans + "(" + str + ") ", used);
                used[i] = false;
            }
        }
        used[idx] = false;
        return count;
    }

    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1#
    // friends Pairing Integer version(Converted from string approach)
    public static int friendPairingInteger(int n, String ans, boolean[] used) {
        int idx = 1;
        while (idx < used.length) {
            if (used[idx] == false) {
                break;
            }
            idx++;
        }
        if (idx > n) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        used[idx] = true;
        count += friendPairingInteger(n, ans + "(" + idx + ") ", used);
        for (int i = idx + 1; i <= n; i++) {
            if (used[i] == false) {
                used[i] = true;
                count += friendPairingInteger(n, ans + "(" + idx+""+i + ") ", used);
                used[i] = false;
            }
        }
        used[idx] = false;
        return count;
    }

    public static void main(String[] args) {
        boolean[] used = new boolean[5];
        // friendPairingString1("ABCD", "", used);
        friendPairingInteger(4, "", used);
    }
}
