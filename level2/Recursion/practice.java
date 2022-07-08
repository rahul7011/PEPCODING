import java.util.ArrayList;

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
        System.out.println(queenPerm_optimised(n, m, m, 0, ""));
    }

    public static void main(String[] args) {

        // permAndComb();
        // permAndCombSubseq();
        queensCall();

    }
}
