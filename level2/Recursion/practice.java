import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class practice {
    private static int perInfi(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += perInfi(coins, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    private static int combInfi(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += combInfi(coins, i, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    private static int combSing(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += combSing(coins, i + 1, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    private static int perSing(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > 0 && tar - coins[i] >= 0) {
                coins[i] *= (-1);
                count += perSing(coins, tar - (coins[i] * (-1)), psf + (coins[i] * (-1)) + " ");
                coins[i] *= (-1);
            }
        }
        return count;
    }

    private static int combSingSubseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += combSingSubseq(coins, idx + 1, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += combSingSubseq(coins, idx + 1, tar, psf);
        return count;
    }

    private static int permInfiSubseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += permInfiSubseq(coins, 0, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += permInfiSubseq(coins, idx + 1, tar, psf);
        return count;
    }

    private static int combInfiSubseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += combInfiSubseq(coins, idx, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += combInfiSubseq(coins, idx + 1, tar, psf);
        return count;
    }

    private static int permSingSubseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (coins[idx] > 0 && tar - coins[idx] >= 0) {
            coins[idx] *= (-1);
            count += permSingSubseq(coins, 0, tar - (coins[idx] * (-1)), psf + (coins[idx] * (-1)) + " ");
            coins[idx] *= (-1);
        }
        count += permSingSubseq(coins, idx + 1, tar, psf);
        return count;
    }

    private static void permAndComb() {
        int[] coins = { 2, 3, 5, 7 };
        int tar = 10;
        System.out.println(perInfi(coins, tar, ""));
        System.out.println(combInfi(coins, 0, tar, ""));
        System.out.println(combSing(coins, 0, tar, ""));
        System.out.println(perSing(coins, tar, ""));
    }

    private static void permAndCombSubseq()

    {
        int[] coins = { 2, 3, 5, 7 };
        int tar = 10;
        // System.out.println(combSingSubseq(coins, 0, tar, ""));
        // System.out.println(permInfiSubseq(coins, 0, tar, ""));
        // System.out.println(combInfiSubseq(coins, 0, tar, ""));
        System.out.println(permSingSubseq(coins, 0, tar, ""));

    }

    // Recursion and Backtracking
    private static int queenComb(int tnb, int cb, int tnq, int cq, String psf) {
        if (cq == tnq || cb == tnb) {
            if (cq == tnq) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for (int i = cb; i < tnb; i++) {
            count += queenComb(tnb, i + 1, tnq, cq + 1, psf + "b" + i + "q" + cq + " ");
        }
        return count;
    }

    private static int queenPerm(int tnb, int cb, int tnq, int cq, String psf, int vis) {
        if (cq == tnq) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = cb; i < tnb; i++) {
            int mask = (1 << i);
            if ((vis & mask) == 0) {
                vis ^= mask;
                count += queenPerm(tnb, 0, tnq, cq + 1, psf + "b" + i + "q" + cq + " ", vis);
                vis ^= mask;
            }
        }
        return count;
    }

    private static int queenComb2D(boolean[][] board, int cb, int tnq, int cq, String psf) {
        if (cq == tnq) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, n = board.length, m = board[0].length;
        for (int i = cb; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            count += queenComb2D(board, i + 1, tnq, cq + 1, psf + "(" + r + "," + c + ") ");
        }
        return count;
    }

    // Note:Since we are using bits for checking therefore it is limited upto5*6
    // matrix(32 bit limit on bits)
    private static int queenPerm2D(boolean[][] board, int cb, int tnq, String psf, int vis) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, n = board.length, m = board[0].length;
        for (int i = cb; i < n * m; i++) {
            int mask = (1 << i);
            int r = i / m;
            int c = i % m;
            if ((vis & mask) == 0) {
                vis ^= mask;
                count += queenPerm2D(board, 0, tnq - 1, psf + "(" + r + "," + c + ") ", vis);
                vis ^= mask;
            }
        }
        return count;
    }

    // Now we will be implementing isSafe function()
    private static boolean isSafedir4(boolean[][] board, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < board.length; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                    if (board[x][y] == true) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int queenComb(boolean[][] board, int cb, int tnq, int cq, String psf) {
        if (cq == tnq) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, n = board.length, m = board[0].length;
        for (int i = cb; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafedir4(board, r, c) == true) {
                board[r][c] = true;
                count += queenComb(board, i + 1, tnq, cq + 1, psf + "(" + r + "," + c + ") ");
                board[r][c] = false;
            }
        }
        return count;
    }

    private static boolean isSafeAlldir(boolean[][] board, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < board.length; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                    if (board[x][y] == true) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int queenPerm(boolean[][] board, int cb, int tnq, String psf, int vis) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, n = board.length, m = board[0].length;
        for (int i = cb; i < n * m; i++) {
            int mask = (1 << i);
            int r = i / m;
            int c = i % m;
            if ((vis & mask) == 0 && isSafeAlldir(board, r, c) == true) {
                vis ^= mask;
                board[r][c] = true;
                count += queenPerm(board, 0, tnq - 1, psf + "(" + r + "," + c + ") ", vis);
                board[r][c] = false;
                vis ^= mask;
            }
        }
        return count;
    }

    // Using shadow technique to reduce the time and Space of checking the safe
    // place
    private static boolean[] row;
    private static boolean[] col;
    private static boolean[] diag;
    private static boolean[] adiag;

    private static int queenComb_shadow(int n, int m, int cb, int tnq, int cq, String psf) {
        if (cq == tnq) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = cb; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (row[r] == false && col[c] == false && diag[r + c] == false && adiag[r - c + m - 1] == false) {
                row[r] = true;
                col[c] = true;
                diag[r + c] = true;
                adiag[r - c + m - 1] = true;
                count += queenComb_shadow(n, m, i + 1, tnq, cq + 1, psf + "(" + r + "," + c + ") ");
                row[r] = false;
                col[c] = false;
                diag[r + c] = false;
                adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    private static int queenPerm_shadow(int n, int m, int cb, int tnq, String psf, int vis) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = cb; i < n * m; i++) {
            int mask = (1 << i);
            int r = i / m;
            int c = i % m;
            if ((vis & mask) == 0
                    && (row[r] == false && col[c] == false && diag[r + c] == false && adiag[r - c + m - 1] == false)) {
                vis ^= mask;
                row[r] = true;
                col[c] = true;
                diag[r + c] = true;
                adiag[r - c + m - 1] = true;
                count += queenPerm_shadow(n, m, 0, tnq - 1, psf + "(" + r + "," + c + ") ", vis);
                row[r] = false;
                col[c] = false;
                diag[r + c] = false;
                adiag[r - c + m - 1] = false;
                vis ^= mask;
            }
        }
        return count;
    }

    // now we are optimising nqueens ,only placing a single queen at a single row
    // Terminologies:Floors and rooms and isme hum floors(cols(only m--> mC1)) pe
    // call lga rhe hai whereas phle wale mein hum rooms((n*m)C1) pe call rhe the
    private static int queenComb_optimised(int n, int m, int floor, String psf) {
        if (floor == m) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (row[r] == false && col[c] == false && diag[r + c] == false && adiag[r - c + m - 1] == false) {
                row[r] = true;
                col[c] = true;
                diag[r + c] = true;
                adiag[r - c + m - 1] = true;
                count += queenComb_optimised(n, m, r + 1, psf + "(" + r + "," + c + ") ");
                row[r] = false;
                col[c] = false;
                diag[r + c] = false;
                adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    private static int queenPerm_optimised(int n, int m, int tnq, int floor, String psf) {
        if (floor == m || tnq == 0) {
            if (tnq == 0)
                System.out.println(psf);
            return tnq == 0 ? 1 : 0;
        }
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (row[r] == false && col[c] == false && diag[r + c] == false && adiag[r - c + m - 1] == false) {
                row[r] = true;
                col[c] = true;
                diag[r + c] = true;
                adiag[r - c + m - 1] = true;
                count += queenPerm_optimised(n, m, tnq - 1, 0, psf + "(" + r + "," + c + ") ");
                row[r] = false;
                col[c] = false;
                diag[r + c] = false;
                adiag[r - c + m - 1] = false;
            }
        }
        int r = floor;
        count += queenPerm_optimised(n, m, tnq, r + 1, psf);
        return count;
    }

    private static void queensCall() {
        // System.out.println(queenComb(5, 0, 3, 0, ""));
        // System.out.println(queenPerm(5, 0, 3, 0, "", 0));
        boolean[][] board = new boolean[4][4];
        // System.out.println(queenComb2D(board, 0, 4, 0, ""));
        // System.out.println(queenPerm2D(board, 0, 4, "", 0));

        // System.out.println(queenComb(board, 0, 4, 0, ""));
        // System.out.println(queenPerm(board, 0, 4, "", 0));

        int n = board.length, m = board[0].length;
        row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];
        // System.out.println(queenComb_shadow(n, m, 0, 4, 0, ""));
        // System.out.println(queenPerm_shadow(n, m, 0, 4, "", 0));

        // System.out.println(queenComb_optimised(n, m, 0, ""));
        // System.out.println(queenPerm_optimised(n, m, m, 0, ""));

        row_bits = 0;
        col_bits = 0;
        diag_bits = 0;
        adiag_bits = 0;
        System.out.println(queenComb_optimised_bit(n, m, 0, ""));
    }

    // https://leetcode.com/problems/n-queens-ii/
    class Solution {
        private static boolean[] row;
        private static boolean[] col;
        private static boolean[] diag;
        private static boolean[] adiag;

        private static int queenComb_optimised(int n, int m, int floor, String psf) {
            if (floor == m) {
                // System.out.println(psf);
                return 1;
            }
            int count = 0;
            for (int room = 0; room < m; room++) {
                int r = floor, c = room;
                if (row[r] == false && col[c] == false && diag[r + c] == false && adiag[r - c + m - 1] == false) {
                    row[r] = true;
                    col[c] = true;
                    diag[r + c] = true;
                    adiag[r - c + m - 1] = true;
                    count += queenComb_optimised(n, m, r + 1, psf + "(" + r + "," + c + ") ");
                    row[r] = false;
                    col[c] = false;
                    diag[r + c] = false;
                    adiag[r - c + m - 1] = false;
                }
            }
            return count;
        }

        public int totalNQueens(int n) {
            int m = n;
            row = new boolean[n];
            col = new boolean[m];
            diag = new boolean[n + m - 1];
            adiag = new boolean[n + m - 1];
            return queenComb_optimised(n, m, 0, "");
        }
    }

    // https://leetcode.com/problems/n-queens/
    class Solution1 {
        private static boolean[] row;
        private static boolean[] col;
        private static boolean[] diag;
        private static boolean[] adiag;

        private static int queenComb_optimised(int n, int m, int floor, List<List<String>> ans, List<String> smallAns) {
            if (floor == m) {
                // System.out.println(psf);
                ans.add(new ArrayList<>(smallAns));
                return 1;
            }
            int count = 0;
            StringBuilder psf = new StringBuilder();
            for (int room = 0; room < m; room++) {
                int r = floor, c = room;
                if (row[r] == false && col[c] == false && diag[r + c] == false && adiag[r - c + m - 1] == false) {
                    row[r] = true;
                    col[c] = true;
                    diag[r + c] = true;
                    adiag[r - c + m - 1] = true;
                    String qsf = "";
                    for (int place = 0; place < m; place++) {
                        if (place != (c)) {
                            qsf += '.';
                        } else {
                            qsf += 'Q';
                        }
                    }
                    smallAns.add(qsf);
                    count += queenComb_optimised(n, m, r + 1, ans, smallAns);
                    row[r] = false;
                    col[c] = false;
                    diag[r + c] = false;
                    adiag[r - c + m - 1] = false;
                    smallAns.remove(smallAns.size() - 1);
                }
            }
            return count;
        }

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> ans = new ArrayList<>();
            List<String> smallAns = new ArrayList<>();
            int m = n;
            row = new boolean[n];
            col = new boolean[m];
            diag = new boolean[n + m - 1];
            adiag = new boolean[n + m - 1];
            System.out.println(queenComb_optimised(n, m, 0, ans, smallAns));
            return ans;
        }
    }

    // Now we are optimising with the bits(Maximum optimisation that is possible)
    private static int row_bits;
    private static int col_bits;
    private static int diag_bits;
    private static int adiag_bits;

    private static int queenComb_optimised_bit(int n, int m, int floor, String psf) {
        if (floor == m) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if ((row_bits & (1 << r)) == 0 && (col_bits & (1 << c)) == 0 && (diag_bits & (1 << (r + c))) == 0
                    && (adiag_bits & (1 << (r - c + m - 1))) == 0) {
                row_bits ^= (1 << r);
                col_bits ^= (1 << c);
                diag_bits ^= (1 << (r + c));
                adiag_bits ^= (1 << (r - c + m - 1));

                count += queenComb_optimised_bit(n, m, r + 1, psf + "(" + r + "," + c + ") ");
                row_bits ^= (1 << r);
                col_bits ^= (1 << c);
                diag_bits ^= (1 << (r + c));
                adiag_bits ^= (1 << (r - c + m - 1));
            }
        }
        return count;
    }

    // https://leetcode.com/problems/n-queens-ii/
    class Solution2 {
        private static int row;
        private static int col;
        private static int diag;
        private static int adiag;

        private static int queenComb_optimised(int n, int m, int floor, String psf) {
            if (floor == m) {
                // System.out.println(psf);
                return 1;
            }
            int count = 0;
            for (int room = 0; room < m; room++) {
                int r = floor, c = room;
                if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r + c))) == 0
                        && (adiag & (1 << (r - c + m - 1))) == 0) {
                    row ^= (1 << r);
                    col ^= (1 << c);
                    diag ^= (1 << (r + c));
                    adiag ^= (1 << (r - c + m - 1));
                    count += queenComb_optimised(n, m, r + 1, psf + "(" + r + "," + c + ") ");
                    row ^= (1 << r);
                    col ^= (1 << c);
                    diag ^= (1 << (r + c));
                    adiag ^= (1 << (r - c + m - 1));
                }
            }
            return count;
        }

        public int totalNQueens(int n) {
            int m = n;
            row = 0;
            col = 0;
            diag = 0;
            adiag = 0;
            return queenComb_optimised(n, m, 0, "");
        }
    }

    // https://leetcode.com/problems/n-queens/
    class Solution3 {
        private static int row;
        private static int col;
        private static int diag;
        private static int adiag;

        private static int queenComb_optimised(int n, int m, int floor, List<List<String>> ans, List<String> smallAns) {
            if (floor == m) {
                // System.out.println(psf);
                ans.add(new ArrayList<>(smallAns));
                return 1;
            }
            int count = 0;
            StringBuilder psf = new StringBuilder();
            for (int room = 0; room < m; room++) {
                int r = floor, c = room;
                if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r + c))) == 0
                        && (adiag & (1 << (r - c + m - 1))) == 0) {
                    row ^= (1 << r);
                    col ^= (1 << c);
                    diag ^= (1 << (r + c));
                    adiag ^= (1 << (r - c + m - 1));
                    String qsf = "";
                    for (int place = 0; place < m; place++) {
                        if (place != (c)) {
                            qsf += '.';
                        } else {
                            qsf += 'Q';
                        }
                    }
                    smallAns.add(qsf);
                    count += queenComb_optimised(n, m, r + 1, ans, smallAns);
                    row ^= (1 << r);
                    col ^= (1 << c);
                    diag ^= (1 << (r + c));
                    adiag ^= (1 << (r - c + m - 1));
                    smallAns.remove(smallAns.size() - 1);
                }
            }
            return count;
        }

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> ans = new ArrayList<>();
            List<String> smallAns = new ArrayList<>();
            int m = n;
            row = 0;
            col = 0;
            diag = 0;
            adiag = 0;
            System.out.println(queenComb_optimised(n, m, 0, ans, smallAns));
            return ans;
        }
    }

    // ========Backtracking==========
    private static boolean isSafeToPlaceNumber(char[][] board, int r, int c, int num) {
        // row and col
        for (int i = 0; i < board.length; i++) {
            if (board[r][i] == (char) (num + '0') || board[i][c] == (char) (num + '0')) {
                return false;
            }
        }
        // for it's own 3*3 block
        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] == (char) (num + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean solveSudoku(char[][] board, ArrayList<Integer> emptyidx, int idx) {
        if (idx == emptyidx.size()) {
            return true;
        }
        int cell = emptyidx.get(idx);
        int r = cell / 9;
        int c = cell % 9;
        for (int num = 1; num <= 9; num++) {
            if (isSafeToPlaceNumber(board, r, c, num) == true) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, emptyidx, idx + 1) == true) {
                    return true;
                }
                board[r][c] = '.';
            }
        }
        return false;
    }

    private static int[] row_sudoku = new int[9];
    private static int[] col_sudoku = new int[9];
    private static int[][] mat_sudoku = new int[3][3];

    private static boolean solveSudoku_optimised(char[][] board, ArrayList<Integer> emptyidx, int idx) {
        if (idx == emptyidx.size()) {
            return true;
        }
        int cell = emptyidx.get(idx);
        int r = cell / 9;
        int c = cell % 9;
        for (int num = 1; num <= 9; num++) {
            int mask = (1 << num);
            if ((row_sudoku[r] & mask) == 0 && (col_sudoku[c] & mask) == 0 && (mat_sudoku[r / 3][c / 3] & mask) == 0) {
                board[r][c] = (char) (num + '0');
                row_sudoku[r] ^= mask;
                col_sudoku[c] ^= mask;
                mat_sudoku[r / 3][c / 3] ^= mask;
                if (solveSudoku_optimised(board, emptyidx, idx + 1) == true) {
                    return true;
                }
                row_sudoku[r] ^= mask;
                col_sudoku[c] ^= mask;
                mat_sudoku[r / 3][c / 3] ^= mask;
                board[r][c] = '.';
            }
        }
        return false;
    }

    private static int friendsPairing_string(String s, String psf, boolean[] visited) {
        int idx = 0;
        while (idx < s.length()) {
            if (visited[idx] == false) {
                break;
            }
            idx++;
        }
        if (idx == s.length()) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        visited[idx] = true;
        count += friendsPairing_string(s, psf + "(" + s.charAt(idx) + ") ", visited);
        for (int i = idx + 1; i < s.length(); i++) {
            if (visited[i] == false) {
                visited[i] = true;
                count += friendsPairing_string(s, psf + "(" + s.charAt(idx) + "" + s.charAt(i) + ") ", visited);
                visited[i] = false;
            }
        }
        visited[idx] = false;
        return count;
    }

    private static int friendsPairing_number(int n, String psf, boolean[] visited) {
        int idx = 1;
        while (idx <= n) {
            if (visited[idx] == false) {
                break;
            }
            idx++;
        }
        if (idx == n + 1) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        visited[idx] = true;
        count += friendsPairing_number(n, psf + "(" + idx + ") ", visited);
        for (int i = idx + 1; i <= n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                count += friendsPairing_number(n, psf + "(" + idx + "" + i + ") ", visited);
                visited[i] = false;
            }
        }
        visited[idx] = false;
        return count;
    }

    private static void backtracking() {
        // ArrayList<Integer> emptyidx = new ArrayList<>();
        // char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
        // { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.',
        // '.', '.', '6', '.' },
        // { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.',
        // '3', '.', '.', '1' },
        // { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.',
        // '.', '2', '8', '.' },
        // { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8',
        // '.', '.', '7', '9' } };

        // for (int i = 0; i < 9; i++) {
        // for (int j = 0; j < 9; j++) {
        // if (board[i][j] == '.') {
        // emptyidx.add(i * 9 + j);
        // } else {
        // int mask = (1 << (board[i][j] - '0'));
        // row_sudoku[i] ^= mask;
        // col_sudoku[j] ^= mask;
        // mat_sudoku[i / 3][j / 3] ^= mask;
        // }
        // }
        // }
        // // System.out.println(solveSudoku(board, emptyidx, 0));
        // System.out.println(solveSudoku_optimised(board, emptyidx, 0));
        // for (int i = 0; i < board.length; i++) {
        // for (int j = 0; j < board.length; j++) {
        // System.out.print(board[i][j] + " ");
        // }
        // System.out.println();
        // }
        // boolean[] visited = new boolean["ABCD".length()];
        // System.out.println(friendsPairing_string("ABCD", "", visited));
        boolean[] visited = new boolean[4 + 1];// 1-based indexing
        System.out.println(friendsPairing_number(4, "", visited));
    }

    // Questions given in class of backtracking
    // https://practice.geeksforgeeks.org/problems/largest-number-in-k-swaps-1587115620/1/#
    class Solution4 {
        private static String swap(String str, int i, int j) {
            char ith = str.charAt(i);
            char jth = str.charAt(j);

            String left = str.substring(0, i);
            String middle = str.substring(i + 1, j);
            String right = str.substring(j + 1);

            return left + jth + middle + ith + right;
        }

        private static String findMaxNum(String s, int k) {
            if (k == 0) {
                // System.out.println(s);
                return new String(s);
            }
            String ans = s;
            for (int i = 0; i < s.length() - 1; i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) > s.charAt(i)) {
                        s = swap(s, i, j);
                        String check = findMaxNum(s, k - 1);
                        if (ans.compareTo(check) < 0) {
                            ans = new String(check);
                        }
                        s = swap(s, i, j);
                    }

                }
            }
            // return ans.compareTo(s)<0?new String(ans):ans;
            return ans;
        }

        // Function to find the largest number after k swaps.
        public static String findMaximumNum(String str, int k) {
            return findMaxNum(str, k);
        }
    }

    // Cross word
    private static boolean isPossibleToPlace_H(char[][] board, String word, int r, int c) {
        int l = word.length(), m = board[0].length;
        if (c + l > m) {
            return false;
        }

        if (c == 0 && c + l < m && board[r][c + l] != '+') {
            return false;
        }
        if (c != 0 && c + l == m && board[r][c - 1] != '+') {
            return false;
        }

        if (c != 0 && c + l < m && board[r][c - 1] != '+' && board[r][c + l] != '+') {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (board[r][c + i] != '-' && board[r][c + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static int place_H(char[][] board, String word, int r, int c) {
        int loc = 0; // location in bits
        for (int i = 0; i < word.length(); i++) {
            if (board[r][c + i] == '-') {
                int mask = (1 << i);
                loc ^= mask;
                board[r][c + i] = word.charAt(i);
            }
        }
        return loc;
    }

    private static void unplace_H(char[][] board, String word, int r, int c, int loc) {
        for (int i = 0; i < word.length(); i++) {
            int mask = (1 << i);
            if ((loc & mask) != 0) {
                board[r][c + i] = '-';
            }
        }
    }

    private static boolean isPossibleToPlace_V(char[][] board, String word, int r, int c) {
        int l = word.length(), n = board.length;
        if (r + l > n) {
            return false;
        }

        if (r == 0 && r + l < n && board[r + l][c] != '+') {
            return false;
        }
        if (r != 0 && r + l == n && board[r - 1][c] != '+') {
            return false;
        }

        if (r != 0 && r + l < n && board[r - 1][c] != '+' && board[r + l][c] != '+') {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (board[r + i][c] != '-' && board[r + i][c] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static int place_V(char[][] board, String word, int r, int c) {
        int loc = 0; // location in bits
        for (int i = 0; i < word.length(); i++) {
            if (board[r + i][c] == '-') {
                int mask = (1 << i);
                loc ^= mask;
                board[r + i][c] = word.charAt(i);
            }
        }
        return loc;
    }

    private static void unplace_V(char[][] board, String word, int r, int c, int loc) {
        for (int i = 0; i < word.length(); i++) {
            int mask = (1 << i);
            if ((loc & mask) != 0) {
                board[r + i][c] = '-';
            }
        }
    }

    private static int crossWord(char[][] board, String[] words, int idx) {
        if (idx == words.length) {
            print(board);
            return 1;
        }
        String word = words[idx];
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '-' || board[i][j] == word.charAt(0)) {
                    if (isPossibleToPlace_H(board, word, i, j) == true) {
                        int loc = place_H(board, word, i, j);
                        count += crossWord(board, words, idx + 1);
                        unplace_H(board, word, i, j, loc);
                    }
                    if (isPossibleToPlace_V(board, word, i, j) == true) {
                        int loc = place_V(board, word, i, j);
                        count += crossWord(board, words, idx + 1);
                        unplace_V(board, word, i, j, loc);
                    }
                }
            }
        }
        return count;
    }

    private static void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void crossWordCall() {
        char[][] board = {
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' }
        };
        String[] words = { "agra", "norway", "england", "gwalior" };
        System.out.println(crossWord(board, words, 0));
    }

    // HackerRank Crossword puzzle
    // https://www.hackerrank.com/challenges/crossword-puzzle/problem

    class Result {

        /*
         * Complete the 'crosswordPuzzle' function below.
         *
         * The function is expected to return a STRING_ARRAY.
         * The function accepts following parameters:
         * 1. STRING_ARRAY crossword
         * 2. STRING words
         */

        private static List<String> ans = new ArrayList<>();

        private static boolean isPossibleToPlace_H(List<String> board, String word, int r, int c) {
            int l = word.length(), m = 10;
            if (c + l > m) {
                return false;
            }

            if (c == 0 && c + l < m && board.get(r).charAt(c + l) != '+') {
                return false;
            }
            if (c != 0 && c + l == m && board.get(r).charAt(c - 1) != '+') {
                return false;
            }

            if (c != 0 && c + l < m && board.get(r).charAt(c - 1) != '+' && board.get(r).charAt(c + l) != '+') {
                return false;
            }

            for (int i = 0; i < word.length(); i++) {
                if (board.get(r).charAt(c + i) != '-' && board.get(r).charAt(c + i) != word.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        private static int place_H(List<String> board, String word, int r, int c) {
            int loc = 0; // location in bits
            for (int i = 0; i < word.length(); i++) {
                if (board.get(r).charAt(c + i) == '-') {
                    int mask = (1 << i);
                    loc ^= mask;
                    StringBuilder s = new StringBuilder(board.get(r));
                    s.setCharAt(c + i, word.charAt(i));
                    board.set(r, new String(s));
                }
            }
            return loc;
        }

        private static void unplace_H(List<String> board, String word, int r, int c, int loc) {
            for (int i = 0; i < word.length(); i++) {
                int mask = (1 << i);
                if ((loc & mask) != 0) {
                    StringBuilder s = new StringBuilder(board.get(r));
                    s.setCharAt(c + i, '-');
                    board.set(r, new String(s));
                }
            }
        }

        private static boolean isPossibleToPlace_V(List<String> board, String word, int r, int c) {
            int l = word.length(), n = 10;
            if (r + l > n) {
                return false;
            }

            if (r == 0 && r + l < n && board.get(r + l).charAt(c) != '+') {
                return false;
            }
            if (r != 0 && r + l == n && board.get(r - 1).charAt(c) != '+') {
                return false;
            }

            if (r != 0 && r + l < n && board.get(r - 1).charAt(c) != '+' && board.get(r + l).charAt(c) != '+') {
                return false;
            }

            for (int i = 0; i < word.length(); i++) {
                if (board.get(r + i).charAt(c) != '-' && board.get(r + i).charAt(c) != word.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        private static int place_V(List<String> board, String word, int r, int c) {
            int loc = 0; // location in bits
            for (int i = 0; i < word.length(); i++) {
                if (board.get(r + i).charAt(c) == '-') {
                    int mask = (1 << i);
                    loc ^= mask;
                    StringBuilder s = new StringBuilder(board.get(r + i));
                    s.setCharAt(c, word.charAt(i));
                    board.set(r + i, new String(s));
                }
            }
            return loc;
        }

        private static void unplace_V(List<String> board, String word, int r, int c, int loc) {
            for (int i = 0; i < word.length(); i++) {
                int mask = (1 << i);
                if ((loc & mask) != 0) {
                    StringBuilder s = new StringBuilder(board.get(r + i));
                    s.setCharAt(c, '-');
                    board.set(r + i, new String(s));
                }
            }
        }

        private static int crossWord(List<String> board, String[] words, int idx) {
            if (idx == words.length) {
                ans = new ArrayList<>(board);
                return 1;
            }
            String word = words[idx];
            int count = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board.get(i).charAt(j) == '-' || board.get(i).charAt(j) == word.charAt(0)) {
                        if (isPossibleToPlace_H(board, word, i, j) == true) {
                            int loc = place_H(board, word, i, j);
                            count += crossWord(board, words, idx + 1);
                            unplace_H(board, word, i, j, loc);
                        }
                        if (isPossibleToPlace_V(board, word, i, j) == true) {
                            int loc = place_V(board, word, i, j);
                            count += crossWord(board, words, idx + 1);
                            unplace_V(board, word, i, j, loc);
                        }
                    }
                }
            }
            return count;
        }

        public static List<String> crosswordPuzzle(List<String> crossword, String str) {
            String[] words = str.split(";");
            crossWord(crossword, words, 0);
            return ans;
        }

    }

    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1/#

    class Solution5 {
        private static int maxGold(int[][] arr, int r, int c, int[][] dir) {
            int ans = 0;
            for (int d = 0; d < dir.length; d++) {
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if (x >= 0 && y >= 0 && x < arr.length && y < arr[0].length) {
                    ans = Math.max(ans, maxGold(arr, x, y, dir));
                }
            }
            return ans + arr[r][c];
        }

        static int maxGold(int n, int m, int arr[][]) {
            int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans = Math.max(ans, maxGold(arr, i, 0, dir));
            }
            return ans;
        }
    }

    private static int kSubsetSum(int[] arr, int idx, int[] setSum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            for (int i = 1; i < setSum.length; i++) {
                if (setSum[i - 1] != setSum[i]) {
                    return 0;
                }
            }
            for (ArrayList<Integer> list : ans) {
                System.out.print(list);
            }
            System.out.println();
            return 1;
        }
        int count = 0, k = setSum.length;
        for (int i = 0; i < k; i++) {
            setSum[i] += arr[idx];
            ans.get(i).add(arr[idx]);

            count += kSubsetSum(arr, idx + 1, setSum, ans);

            setSum[i] -= arr[idx];
            // leader and non-leader concept
            // if it is a leader then break else make call for other subsets
            ans.get(i).remove(ans.get(i).size() - 1);

            if (ans.get(i).size() == 0) {
                break;
            }
        }
        return count;
    }

    private static int kPartition(int n, int idx, ArrayList<ArrayList<Integer>> ans) {
        if (idx > n) {
            for (ArrayList<Integer> list : ans) {
                if (list.size() == 0) {
                    return 0;
                }
            }
            for (ArrayList<Integer> list : ans) {
                System.out.print(list);
            }
            System.out.println();
            return 1;
        }
        int count = 0, k = ans.size();
        for (int i = 0; i < k; i++) {
            ans.get(i).add(idx);

            count += kPartition(n, idx + 1, ans);

            ans.get(i).remove(ans.get(i).size() - 1);
            // leader and non-leader concept
            // if it is a leader then break else move on!
            if (ans.get(i).size() == 0) {
                break;
            }
        }
        return count;
    }

    // CryptArithmetic
    static int[] map = new int[26];
    static boolean[] isUsed = new boolean[10];

    private static int StringToInteger(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            res = res * 10 + (map[ch - 'a']);
        }
        return res;
    }

    private static int crypto(String unique, int idx, String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int a = StringToInteger(s1);
            int b = StringToInteger(s2);
            int c = StringToInteger(s3);
            if (a + b == c) {
                System.out.println(a + "\n+" + b + "\n-----\n" + c);
                System.out.println();
                return 1;
            }
            return 0;
        }
        // choices
        int count = 0;
        char ch = unique.charAt(idx);
        for (int num = 0; num < 10; num++) {
            if ((ch == s1.charAt(0) || ch == s2.charAt(0) || ch == s3.charAt(0)) && (num == 0)) {
                continue;
            }
            if (isUsed[num] == false) {
                isUsed[num] = true;
                map[ch - 'a'] = num;
                count += crypto(unique, idx + 1, s1, s2, s3);
                map[ch - 'a'] = -1;
                isUsed[num] = false;
            }
        }
        return count;
    }

    private static void crypto(String s1, String s2, String s3) {
        // forming string of only unique characters
        String s = s1 + s2 + s3;
        boolean[] freq = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a'] = true;
        }
        s = "";
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == true) {
                char ch = (char) (i + 'a');
                s += ch;
            }
        }
        Arrays.fill(map, -1);
        System.out.println(crypto(s, 0, s1, s2, s3));
    }

    private static void subSetCall() {
        // ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // int k = 2;
        // int[] setSum = new int[k];
        // for (int i = 0; i < k; i++) {
        // ans.add(new ArrayList<>());
        // }
        // int[] arr = { 1, 2, 3, 4, 5, 6 };
        // kSubsetSum(arr, 0, setSum, ans);

        // for (int i = 0; i < k; i++) {
        // ans.add(new ArrayList<>());
        // }
        // kPartition(3, 1, ans);

        crypto("send", "more", "money");
        // crypto("base", "ball", "games");
        // crypto("your", "you", "heart");

    }

    public static void main(String[] args) {

        // permAndComb();
        // permAndCombSubseq();
        // queensCall();
        // backtracking();
        // crossWordCall();
        subSetCall();
    }
}
