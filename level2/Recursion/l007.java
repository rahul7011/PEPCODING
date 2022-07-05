public class l007 {

    // Easy recursion
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/crossword-puzzle-official/ojquestion
    public static boolean isPossibleToPlace_H(char[][] board, String word, int r, int c) {
        int l = word.length(), m = board[0].length;
        // Boundary case from rhs
        if ((c + l) > m)
            return false;
        // from border to lhs
        if (c == 0 && (c + l) < m && board[r][c + l] != '+') {
            return false;
        }
        // from rhs at the border
        if (c != 0 && (c + l) == m && board[r][c - 1] != '+')
            return false;
        // in the middle
        if (c != 0 && (c + l) < m && board[r][c + l] != '+' && board[r][c - 1] != '+')
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (board[r][c + i] != '-' && board[r][c + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int place_H(char[][] board, String word, int r, int c) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (board[r][c + i] == '-') {
                loc ^= (1 << i);
                board[r][c + i] = word.charAt(i);
            }
        }
        return loc;
    }

    public static void unPlace_H(char[][] board, String word, int r, int c, int loc) {
        for (int i = 0; i < word.length(); i++) {
            int mask = (1 << i);
            if ((loc & mask) != 0) {
                board[r][c + i] = '-';
            }
        }
    }

    public static boolean isPossibleToPlace_V(char[][] board, String word, int r, int c) {
        int l = word.length(), n = board[0].length;
        // Boundary case from below
        if ((r + l) > n)
            return false;
        // from top to middle
        if (r == 0 && (r + l) < n && board[r + l][c] != '+') {
            return false;
        }
        // from middle to the bottom
        if (r != 0 && (r + l) == n && board[r - 1][c] != '+')
            return false;
        // in the middle
        if (r != 0 && (r + l) < n && board[r + l][c] != '+' && board[r - 1][c] != '+')
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (board[r + i][c] != '-' && board[r + i][c] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int place_V(char[][] board, String word, int r, int c) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (board[r + i][c] == '-') {
                loc ^= (1 << i);
                board[r + i][c] = word.charAt(i);
            }
        }
        return loc;
    }

    public static void unPlace_V(char[][] board, String word, int r, int c, int loc) {
        for (int i = 0; i < word.length(); i++) {
            int mask = (1 << i);
            if ((loc & mask) != 0) {
                board[r + i][c] = '-';
            }
        }
    }

    public static int crossWord(char[][] board, String[] words, int idx) {
        // System.out.println(idx);
        if (idx == words.length) {
            print(board);
            return 1;
        }
        String word = words[idx];
        int count = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == '-' || board[r][c] == word.charAt(0)) {
                    if (isPossibleToPlace_H(board, word, r, c) == true) {
                        int loc = place_H(board, word, r, c);
                        count += crossWord(board, words, idx + 1);
                        unPlace_H(board, words[idx], r, c, loc);
                    }
                    if (isPossibleToPlace_V(board, words[idx], r, c) == true) {
                        int loc = place_V(board, words[idx], r, c);
                        count += crossWord(board, words, idx + 1);
                        unPlace_V(board, words[idx], r, c, loc);
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

    class Solution {
        private static int mxGold(int[][] mine, int r, int c, int[][] dir) {
            int max = 0, n = mine.length, m = mine[0].length;
            if (c == m - 1) {
                return mine[r][c];
            }

            for (int d = 0; d < dir.length; d++) {
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if (x >= 0 && y >= 0 && x < n && y < m) {
                    int recAns = mxGold(mine, x, y, dir);
                    max = Math.max(max, recAns + mine[r][c]);
                }
            }
            return max;
        }

        static int maxGold(int n, int m, int M[][]) {
            int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
            int max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, mxGold(M, i, 0, dir));
            }
            return max;
        }
    }

    public static void main(String[] args) {
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
}
