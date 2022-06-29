import java.io.*;
import java.util.*;

public class l005 {
    // N queens continue
    public static boolean isSafePlace(boolean[][] board, int r, int c) {
        // up
        for (int i = r; i >= 0; i--) {
            if (board[i][c] == true) {
                return false;
            }
        }
        // left side
        for (int i = 0; i < c + 1; i++) {
            if (board[r][i] == true) {
                return false;
            }
        }
        // left diagonal
        int r1 = r, c1 = c;
        while (Math.min(r1, c1) >= 0) {
            if (board[r1][c1] == true) {
                return false;
            }
            r1--;
            c1--;
        }
        // right diagonal
        while (r >= 0 && c < board[0].length) {
            if (board[r][c] == true) {
                return false;
            }
            r--;
            c++;
        }
        return true;
    }

    public static boolean isSafePlaceAllCovered(boolean[][] board, int r, int c) {
        // up
        for (int i = r; i >= 0; i--) {
            if (board[i][c] == true) {
                return false;
            }
        }
        // down
        for (int i = r; i < board.length; i++) {
            if (board[i][c] == true) {
                return false;
            }
        }
        // left side
        for (int i = 0; i < c + 1; i++) {
            if (board[r][i] == true) {
                return false;
            }
        }
        // right side
        for (int i = c; i < board[0].length; i++) {
            if (board[r][i] == true) {
                return false;
            }
        }
        // left diagonal
        int r1 = r, c1 = c;
        while (Math.min(r1, c1) >= 0) {
            if (board[r1][c1] == true) {
                return false;
            }
            r1--;
            c1--;
        }
        // down left diagonal
        r1 = r;
        c1 = c;
        while (r1 < board.length && c1 >= 0) {
            if (board[r1][c1] == true) {
                return false;
            }
            r1++;
            c1--;
        }
        // down right diagonal
        r1 = r;
        c1 = c;
        while (r1 < board.length && c1 < board[0].length) {
            if (board[r1][c1] == true) {
                return false;
            }
            r1++;
            c1++;
        }
        // right diagonal
        while (r >= 0 && c < board[0].length) {
            if (board[r][c] == true) {
                return false;
            }
            r--;
            c++;
        }
        return true;
    }

    // the above two functions can be easily done like this ;)
    public static boolean isSafeToPlaceQueen(boolean[][] board, int row, int col) {
        /* for only 4 direction(same as isSafePlace()) */
        // int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

        /* for all directions same as isSafePlaceAllCovered() */
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        int n = board.length, m = board[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < board.length; rad++) {
                int r = row + rad * dir[d][0];
                int c = col + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (board[r][c] == true)
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    // combination method
    public static int nqueens01_comb(boolean[][] board, int tnq, int cb, String psf) {
        int n = board.length;
        int m = board[0].length;
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = cb; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafePlace(board, r, c) == true) {
                board[r][c] = true;
                count += nqueens01_comb(board, tnq - 1, i + 1, psf + "(" + r + "," + c + ") ");
                board[r][c] = false;
            }
        }
        return count;
    }

    public static int nqueens_permute(boolean[][] board, int tnq, String psf) {
        int n = board.length;
        int m = board[0].length;
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafePlaceAllCovered(board, r, c) == true && board[r][c] == false) {
                board[r][c] = true;
                count += nqueens_permute(board, tnq - 1, psf + "(" + r + "," + c + ") ");
                board[r][c] = false;
            }
        }
        return count;
    }

    // =======================subseq method====================
    public static int nqueens01_comb_subseq(boolean[][] board, int tnq, int idx, String psf) {
        int n = board.length;
        int m = board[0].length;
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int r = idx / m;
        int c = idx % m;
        if (isSafePlace(board, r, c) == true) {
            board[r][c] = true;
            count += nqueens01_comb_subseq(board, tnq - 1, idx + 1, psf + "(" + r + "," + c + ") ");
            board[r][c] = false;
        }
        count += nqueens01_comb_subseq(board, tnq, idx + 1, psf);
        return count;
    }

    public static int nqueens_permute_subseq(boolean[][] board, int tnq, int idx, String psf) {
        int n = board.length;
        int m = board[0].length;
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int r = idx / m;
        int c = idx % m;
        if (isSafePlaceAllCovered(board, r, c) == true && board[r][c] == false) {
            board[r][c] = true;
            count += nqueens_permute_subseq(board, tnq - 1, 0, psf + "(" + r + "," + c + ") ");
            board[r][c] = false;
        }
        count += nqueens_permute_subseq(board, tnq, idx + 1, psf);
        return count;
    }

    // for best optimisation use bits masking
    public static void queenCall() {
        boolean[][] board = new boolean[4][4];
        // System.out.println(nqueens01_comb(board, 4, 0, ""));
        // System.out.println(nqueens01_comb_subseq(board, 4, 0, ""));
        // System.out.println(nqueens_permute(board, 4, ""));
        System.out.println(nqueens_permute_subseq(board, 4, 0, ""));
    }

    // https://leetcode.com/problems/combination-sum/

    // class Solution {
    // public int combination_infi(int[] coins,int tar,int
    // idx,List<List<Integer>>ans,List<Integer>smallAns)
    // {
    // if(tar==0)
    // {
    // ans.add(new ArrayList<>(smallAns));
    // return 1;
    // }
    // int count=0;
    // for(int i=idx;i<coins.length;i++)
    // {
    // if(tar-coins[i]>=0)
    // {
    // smallAns.add(coins[i]);
    // count+=combination_infi(coins,tar-coins[i],i,ans,smallAns);
    // smallAns.remove(smallAns.size()-1);
    // }
    // }
    // return count;
    // }
    // public List<List<Integer>> combinationSum(int[] candidates, int target) {
    // List<List<Integer>>ans=new ArrayList<>();
    // List<Integer>smallAns=new ArrayList<>();
    // combination_infi(candidates,target,0,ans,smallAns);
    // return ans;
    // }
    // }

    // https://leetcode.com/problems/combination-sum-ii/

    // class Solution {
    // public int combination_infi(int[] coins, int tar, int idx,
    // List<List<Integer>> ans, List<Integer> smallAns) {
    // if (tar == 0) {
    // ans.add(new ArrayList<>(smallAns));
    // return 1;
    // }
    // int count = 0;
    // int prev = -1;
    // for (int i = idx; i < coins.length; i++) {
    // if (prev != coins[i] && tar - coins[i] >= 0) {
    // smallAns.add(coins[i]);
    // count += combination_infi(coins, tar - coins[i], i + 1, ans, smallAns);
    // smallAns.remove(smallAns.size() - 1);
    // }
    // prev = coins[i];
    // }
    // return count;
    // }

    // public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    // List<List<Integer>> ans = new ArrayList<>();
    // List<Integer> smallAns = new ArrayList<>();
    // Arrays.sort(candidates);
    // combination_infi(candidates, target, 0, ans, smallAns);
    // return ans;
    // }
    // }

    // https://leetcode.com/problems/coin-change/
    // class Solution {
    // public int permutation_infi(int[] coins,int tar,int idx)
    // {
    // if(tar==0)
    // {
    // return 0;
    // }
    // int count=(int)1e9;
    // for(int i=idx;i<coins.length;i++)
    // {
    // if(tar-coins[i]>=0)
    // {
    // //wrong method(will return ans even if the ans does not exist)
    // // count=Math.min(count,permutation_infi(coins,tar-coins[i],0)+1);

    // //correct way
    // int recAns=permutation_infi(coins,tar-coins[i],0);
    // if(recAns!=(int)1e9&&count>recAns+1)
    // {
    // count=recAns+1;
    // }
    // }
    // }
    // return count;
    // }
    // public int coinChange(int[] coins, int amount) {
    // int ans=permutation_infi(coins,amount,0);
    // // System.out.println(ans);
    // return ans==(int)1e9?-1:ans;
    // }
    // }

    // https://leetcode.com/problems/coin-change-2/
    // class Solution {
    // public int combination_infi(int[] coins, int tar, int idx) {
    // if (tar == 0) {
    // return 1;
    // }
    // int count = 0;
    // for (int i = idx; i < coins.length; i++) {
    // if (tar - coins[i] >= 0) {
    // count += combination_infi(coins, tar - coins[i], i);
    // }
    // }
    // return count;
    // }

    // public int change(int amount, int[] coins) {
    // return combination_infi(coins, amount, 0);
    // }
    // }

    // https://www.lintcode.com/problem/1308/description
    public class Solution {
        private void getFactors(int n, int factor, List<List<Integer>> ans, List<Integer> smallAns) {
            if (n <= 1) {
                if (smallAns.size() > 1) {
                    List<Integer> base = new ArrayList<>(smallAns);
                    ans.add(base);
                }
                return;
            }
            for (int i = factor; i <= n; i++) {
                if (n % i == 0) {
                    smallAns.add(i);
                    getFactors(n / i, i, ans, smallAns);
                    smallAns.remove(smallAns.size() - 1);
                }
            }
        }

        public List<List<Integer>> getFactors(int n) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> smallAns = new ArrayList<>();
            getFactors(n, 2, ans, smallAns);
            return ans;
        }
    }

    public static void main(String[] args) {
        queenCall();
    }
}
