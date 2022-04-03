import java.io.FileNotFoundException;
import java.io.PrintStream;

public class l4_Queen {
    /*
     * n = total number of places where a queen can be placed
     * queens = total number of queens
     * place = current position where we might be placing the queen
     * qnum = current queen which is about to be placed
     * qsf = queens so far
     */
    public static int queenCombination1D(int[] arr, int n, int queens, int place, int qnum, String qsf) {
        if (place == n || qnum == queens) {
            if (qnum == queens) {
                System.out.println(qsf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += queenCombination1D(arr, n, queens, place + 1, qnum + 1, qsf + "Queen:" + qnum + "@" + place + " ");
        count += queenCombination1D(arr, n, queens, place + 1, qnum, qsf);
        return count;
    }

    /*
     * n = total number of places where a queen can be placed
     * queens = total number of queens
     * place = current position where we might be placing the queen
     * qnum = current queen which is about to be placed
     * qsf = queens so far
     */
    public static int queenPermutation1D(int[] arr, int n, int queens, int place, int qnum, String qsf) {
        if (place == n || qnum == queens) {
            if (qnum == queens) {
                System.out.println(qsf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (arr[place] == 0) {
            arr[place] = 1;
            count += queenPermutation1D(arr, n, queens, 0, qnum + 1, qsf + "Queen:" + qnum + "@" + place + " ");
            arr[place] = 0;
        }
        count += queenPermutation1D(arr, n, queens, place + 1, qnum, qsf);
        return count;
    }

    public static int queenCombination2D(int[][] arr, int rows, int cols, int queens, int idx, int qnum, String qsf) {
        if (qnum == queens) {
            System.out.println(qsf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < rows * cols; i++) {
            count += queenCombination2D(arr, rows, cols, queens, i + 1, qnum + 1,
                    qsf + "Queen:" + qnum + "@(" + (i / rows) + "," + (i % cols) + ") ");
        }
        return count;
    }

    public static int queenPermutation2D(int[][] arr, int rows, int cols, int queens, int idx, int qnum, String qsf) {
        if (qnum == queens) {
            System.out.println(qsf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < rows * cols; i++) {
            if (arr[i / rows][i % cols] == 0) {
                arr[i / rows][i % cols] = 1; // Mark
                count += queenPermutation2D(arr, rows, cols, queens, i + 1, qnum + 1,
                        qsf + "Queen:" + qnum + "@(" + (i / rows) + "," + (i % cols) + ") ");
                arr[i / rows][i % cols] = 0; // UnMark
            }
        }
        return count;
    }

    private static boolean isItSafe(int[][] arr, int idx) {
        int rows = arr.length;
        int cols = arr[0].length;
        int row = idx / rows;
        int col = idx % cols;

        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        // direction fix and radius variable
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.min(rows, cols); rad++) {
                int x = row + rad * dir[d][0];
                int y = col + rad * dir[d][1];
                if (x >= 0 & x < rows && y >= 0 && y < cols) {
                    if (arr[x][y] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static int nqueens_01_Comb(int[][] arr, int rows, int cols, int queens, int idx, int qnum,
            String qsf) {
        if (qnum == queens) {
            System.out.println(qsf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < rows * cols; i++) {
            if (isItSafe(arr, i) == true) {
                arr[i / rows][i % cols] = 1;
                // System.out.println(i / rows + " " + (i % cols)+" "+isItSafe(arr, i));
                count += nqueens_01_Comb(arr, rows, cols, queens, i + 1, qnum + 1,
                        qsf + "Queen:" + qnum + "@(" + (i / rows) + "," + (i % cols) + ") ");
                arr[i / rows][i % cols] = 0;
            }
        }
        return count;
    }

    public static int nqueens_01_Perm(int[][] arr, int rows, int cols, int queens, int idx, int qnum,
            String qsf) {
        if (qnum == queens) {
            System.out.println(qsf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < rows * cols; i++) {
            if (arr[i / rows][i % cols] == 0 && isItSafe(arr, i) == true) {
                arr[i / rows][i % cols] = 1;
                // System.out.println(i / rows + " " + (i % cols)+" "+isItSafe(arr, i));
                count += nqueens_01_Perm(arr, rows, cols, queens, i + 1, qnum + 1,
                        qsf + "Queen:" + qnum + "@(" + (i / rows) + "," + (i % cols) + ") ");
                arr[i / rows][i % cols] = 0;
            }
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream fileOut = new PrintStream("../out.txt");
        System.setOut(fileOut);

        // int[] arr = new int[6];
        // // System.out.println(queenCombination1D(arr, 6, 3, 0, 0, ""));
        // System.out.println(queenPermutation1D(arr,6,3,0,0,""));

        int rows = 4;
        int cols = 4;
        int queens = 4;
        int[][] arr = new int[rows][cols];
        // System.out.println(queenCombination2D(arr, rows, cols, queens, 0, 0, ""));
        // System.out.println(queenPermutation2D(arr, rows, cols, queens, 0, 0, ""));
        // System.out.println(nqueens_01_Comb(arr, rows, cols, queens, 0, 0, ""));
        System.out.println(nqueens_01_Perm(arr, rows, cols, queens, 0, 0, ""));
    }
}

//=============== Homework ========================//

//leetcode 51

// class Solution {
//     private static boolean isItSafe(int[][] arr, int idx) {
//         int rows = arr.length;
//         int cols = arr[0].length;
//         int row = idx / rows;
//         int col = idx % cols;

//         int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
//         // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1
//         // }, { 1, 0 }, { 1, -1 } };
//         // direction fix and radius variable
//         for (int d = 0; d < dir.length; d++) {
//             for (int rad = 1; rad <= Math.min(rows, cols); rad++) {
//                 int x = row + rad * dir[d][0];
//                 int y = col + rad * dir[d][1];
//                 if (x >= 0 & x < rows && y >= 0 && y < cols) {
//                     if (arr[x][y] == 1) {
//                         return false;
//                     }
//                 }
//             }
//         }
//         return true;
//     }

//     private static int solveNQueens(int[][] arr, int rows, int cols, int queens, int idx, int qnum,
//             List<List<String>> ans, List<String> smallAns) {
//         if (qnum == queens) {
//             List<String> list = new ArrayList<>(smallAns);
//             ans.add(list);
//             return 1;
//         }
//         int count = 0;
//         for (int i = idx; i < rows * cols; i++) {
//             if (isItSafe(arr, i) == true) {
//                 arr[i / rows][i % cols] = 1;
//                 String qsf = "";
//                 for (int place = 0; place < cols; place++) {
//                     if (place != (i % cols)) {
//                         qsf += '.';
//                     } else {
//                         qsf += 'Q';
//                     }
//                 }
//                 smallAns.add(qsf);
//                 count += solveNQueens(arr, rows, cols, queens, i + 1, qnum + 1, ans, smallAns);
//                 arr[i / rows][i % cols] = 0;
//                 smallAns.remove(smallAns.size() - 1);
//             }
//         }
//         return count;
//     }

//     public List<List<String>> solveNQueens(int n) {
//         List<List<String>> ans = new ArrayList<>();
//         List<String> smallAns = new ArrayList<>();
//         int[][] arr = new int[n][n];
//         solveNQueens(arr, n, n, n, 0, 0, ans, smallAns);
//         return ans;
//     }
// }

//leetcode 52

// class Solution {
//     private static boolean isItSafe(int[][] arr, int idx) {
//         int rows = arr.length;
//         int cols = arr[0].length;
//         int row = idx / rows;
//         int col = idx % cols;

//         int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
//         // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1
//         // }, { 1, 0 }, { 1, -1 } };
//         // direction fix and radius variable
//         for (int d = 0; d < dir.length; d++) {
//             for (int rad = 1; rad <= Math.min(rows, cols); rad++) {
//                 int x = row + rad * dir[d][0];
//                 int y = col + rad * dir[d][1];
//                 if (x >= 0 & x < rows && y >= 0 && y < cols) {
//                     if (arr[x][y] == 1) {
//                         return false;
//                     }
//                 }
//             }
//         }
//         return true;
//     }
//     private static int totalNQueens(int[][] arr, int rows, int cols, int queens, int idx, int qnum) {
//         if (qnum == queens) {
//             return 1;
//         }
//         int count = 0;
//         for (int i = idx; i < rows * cols; i++) {
//             if (isItSafe(arr, i) == true) {
//                 arr[i / rows][i % cols] = 1;
//                 count += totalNQueens(arr, rows, cols, queens, i + 1, qnum + 1);
//                 arr[i / rows][i % cols] = 0;
//             }
//         }
//         return count;
//     }
//     public int totalNQueens(int n) {
//         int[][] arr=new int[n][n];
//         return totalNQueens(arr,n,n,n,0,0);
//     }
// }