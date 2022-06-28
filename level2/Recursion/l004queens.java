import java.util.ArrayList;

public class l004queens {
    // tnb=total number of boxes,tnq=total number of coins,cb=current box,cq=current
    // queen
    public static int queenCombination_subseq(int tnb, int tnq, int cb, int cq, ArrayList<String> ans, String psf) {
        if (cb == tnb || cq == tnq) {
            if (cq == tnq) {
                ans.add(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += queenCombination(tnb, tnq, cb + 1, cq + 1, ans, psf + "b" + cb + "q" + cq + " ");
        count += queenCombination(tnb, tnq, cb + 1, cq, ans, psf);
        return count;
    }

    public static int queenCombination(int tnb, int tnq, int cb, int cq, ArrayList<String> ans, String psf) {
        if (cb == tnb || cq == tnq) {
            if (cq == tnq) {
                ans.add(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for (int i = cb; i < tnb; i++) {
            count += queenCombination(tnb, tnq, i + 1, cq + 1, ans, psf + "b" + cb + "q" + cq + " ");
        }
        return count;
    }

    // exactly same code as queenCombination just modified to 2D
    public static int queenCombination2D(int[][] arr, int tnq, int cb, ArrayList<String> ans, String psf) {
        int n = arr.length;
        int m = arr[0].length;
        if (tnq == 0) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int i = cb; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            count += queenCombination2D(arr, tnq - 1, i + 1, ans, psf + "(" + r + "," + c + ") ");
        }
        return count;
    }

    public static int queenPermutation_subseq(int tnb, int tnq, int cb, int cq, ArrayList<String> ans, String psf,
            boolean[] marked) {
        if (cb == tnb || cq == tnq) {
            if (cq == tnq) {
                ans.add(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (marked[cb] == false) {
            marked[cb] = true;
            count += queenPermutation_subseq(tnb, tnq, 0, cq + 1, ans, psf + "b" + cb + "q" + cq + " ", marked);
            marked[cb] = false;
        }
        count += queenPermutation_subseq(tnb, tnq, cb + 1, cq, ans, psf, marked);
        return count;
    }

    public static int queenPermutation(int tnb, int tnq, int cb, int cq, ArrayList<String> ans, String psf,
            boolean[] marked) {
        if (cb == tnb || cq == tnq) {
            if (cq == tnq) {
                ans.add(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for (int i = 0; i < tnb; i++) {
            if (marked[i] == false) {
                marked[i] = true;
                count += queenPermutation(tnb, tnq, 0, cq + 1, ans, psf + "b" + i + "q" + cq + " ", marked);
                marked[i] = false;
            }
        }
        return count;
    }

    public static int queenPermutation2D(int[][] arr, int tnq, ArrayList<String> ans, String psf,
            boolean[][] marked) {
        int n = arr.length;
        int m = arr[0].length;
        if (tnq == 0) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n * m; i++) {
            int r=i/m;
            int c=i%m;
            if (marked[r][c] == false) {
                marked[r][c] = true;
                count += queenPermutation2D(arr, tnq - 1,ans, psf + "(" + r + "," + c + ") ", marked);
                marked[r][c] = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> ans = new ArrayList<>();
        // System.out.println(queenCombination(5, 3, 0, 0, ans, ""));
        // System.out.println(ans);
        int[][] arr = new int[4][4];
        // System.out.println(queenCombination2D(arr, 3, 0, ans, ""));
        // System.out.println(ans);
        // boolean[] marked = new boolean[5];
        // System.out.println(queenPermutation(5, 3, 0, 0, ans, "", marked));
        // System.out.println(ans);
        boolean[][] marked = new boolean[4][4];
        System.out.println(queenPermutation2D(arr, 4,ans, "", marked));
        System.out.println(ans);
    }
}
