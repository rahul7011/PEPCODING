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

    private static int queenComb2D(int[][] board, int cb, int tnq, int cq, String psf) {
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
    private static int queenPerm2D(int[][] board, int cb, int tnq, String psf, int vis) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, n = board.length, m = board[0].length;
        for (int i = cb; i < n * m; i++) {
            int mask = (1 << i);
            if ((vis & mask) == 0) {
                vis ^= mask;
                int r = i / mask;
                int c = i % mask;
                count += queenPerm2D(board, 0, tnq - 1, psf + "(" + r + "," + c + ") ", vis);
                vis ^= mask;
            }
        }
        return count;
    }

    private static void queensCall() {
        // System.out.println(queenComb(5, 0, 3, 0, ""));
        // System.out.println(queenPerm(5, 0, 3, 0, "", 0));
        int[][] board = new int[5][5];
        // System.out.println(queenComb2D(board, 0, 4, 0, ""));
        System.out.println(queenPerm2D(board, 0, 4, "", 0));
    }

    public static void main(String[] args) {

        // permAndComb();
        // permAndCombSubseq();
        queensCall();

    }
}
