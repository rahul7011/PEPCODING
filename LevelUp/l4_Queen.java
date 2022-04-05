import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    //leetcode 37(Sudoku solver)

    class Solution {
        private static boolean isItSafe(char[][] board,int r,int c,int num){
            //Compression and Decompression
            int rborder=(r/3)*3;
            int cborder=(c/3)*3;
            
            //num to character
            char check=(char)(num+'0');
            //col
            for(int i=0;i<board.length;i++)
            {
                if(board[i][c]==check)
                {
                    return false;
                }
            }
            //row
            for(int j=0;j<board[0].length;j++)
            {
                if(board[r][j]==check)
                {
                    return false;
                }
            }
            //In its 3*3 block
            for(int i=rborder;i<rborder+3;i++)
            {
                for(int j=cborder;j<cborder+3;j++)
                {
                    if(board[i][j]==check)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        private static boolean solveSudoku(char[][] board,ArrayList<Integer>validPositions,int idx){
            if(idx==validPositions.size())
            {
                return true;
            }
            //decode indices
            int r=validPositions.get(idx)/board.length;
            int c=validPositions.get(idx)%board.length;
            for(int num=1;num<=9;num++)
            {
                if(isItSafe(board,r,c,num)==true)
                {
                    board[r][c]=(char)(num+'0');
                    if(solveSudoku(board,validPositions,idx+1)==true)
                    {
                        return true;
                    }
                    board[r][c]='.';
                }
            }
            return false;
        }
        public void solveSudoku(char[][] board) {
            ArrayList<Integer>validPositions=new ArrayList<>();
            int n=board.length;
            //capturing all the valid positions
            for(int i=0;i<board.length;i++)
            {
                for(int j=0;j<board[0].length;j++)
                {
                    if(board[i][j]=='.')
                    {
                        //Encode row and col
                        validPositions.add(i*n+j);
                    }
                }
            }
            solveSudoku(board,validPositions,0);
        }
    }

    //leetcode 139(Word Break)
    class Solution1 {
        private static boolean wordBreak(String s,HashSet<String>wordDict,int idx,String psf){
            if(idx==s.length())
            {
                System.out.println(psf);
                return true;
            }
            String curr="";
            for(int i=idx;i<s.length();i++)
            {
                curr+=s.charAt(i);
                if(wordDict.contains(curr)==true)
                {
                    if(wordBreak(s,wordDict,i+1,psf+curr+" ")==true)
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        public boolean wordBreak(String s, List<String> wordDict) {
            HashSet<String>hs=new HashSet<>();
            for(String val:wordDict){
                hs.add(val);
            }
            return wordBreak(s,hs,0,"");
        }
    }

    //leetcode 140(Word Break 2)
    class Solution3 {
        private static boolean wordBreak(String s,HashSet<String>wordDict,int idx,String psf,List<String>ans){
            if(idx==s.length())
            {
                System.out.println(psf);
                ans.add(psf.substring(0,psf.length()-1));
                return true;
            }
            String curr="";
            for(int i=idx;i<s.length();i++)
            {
                curr+=s.charAt(i);
                if(wordDict.contains(curr)==true)
                {
                    wordBreak(s,wordDict,i+1,psf+curr+" ",ans);
                }
            }
            return false;
        }
        public List<String> wordBreak(String s, List<String> wordDict) {
            HashSet<String>hs=new HashSet<>();
            for(String val:wordDict){
                hs.add(val);
            }
            List<String> ans=new ArrayList<>();
            wordBreak(s,hs,0,"",ans);
            return ans;
        }
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








//Cryptarithmetic problem
//SEND+MORE=MONEY (9567+1085=10652)

    //My approach
// public class practice {
//     private static boolean Add(String a,String b,String c,int[] mapping)
//     {
//         int left=a.length()-1;
//         int right=b.length()-1;
//         int carry=0;
//         ArrayList<Integer>firstHalf=new ArrayList<>();
//         while(left>=0&&right>=0)
//         {
//             int sum=mapping[a.charAt(left)-'a']+mapping[b.charAt(right)-'a']+carry;
//             firstHalf.add(sum%10);
//             carry=sum/10;
//             left--;
//             right--;
//         }
//         while(left>=0)
//         {
//             int sum=mapping[a.charAt(left)-'a']+carry;
//             firstHalf.add(sum%10);
//             carry=sum/10;
//             left--;
//         }
//         while(right>=0)
//         {
//             int sum=mapping[a.charAt(right)-'a']+carry;
//             firstHalf.add(sum%10);
//             carry=sum/10;
//             right--;
//         }
//         if(carry>0)
//         {
//             firstHalf.add(carry);
//         }
//         Collections.reverse(firstHalf);
//         for (int i = 0; i < c.length(); i++) {
//             if(mapping[c.charAt(i)-'a']!=firstHalf.get(i))
//             {
//                 return false;
//             }
//         }
//         System.out.println(firstHalf);
//         return true;
//     }
//     private static boolean wordBreak(String unique,String a,String b,String c, int idx,int[] mapping,boolean[] visited) {
//         if (idx == unique.length()) {
//             if(Add(a, b, c, mapping)==true)
//             {
//                 return true;
//             }
//             return false;
//         }
//         for (int num = 0; num <= 9; num++) {
//             // System.out.println((unique.charAt(idx)-'a'));
//             // System.out.println(('s'-'a'));
//             if (visited[num]==false) {
//                 if(idx==0&&num==0)
//                 {
                        //Skipped zero for the first position
//                     continue;
//                 }
//                 char curr=unique.charAt(idx);
//                 visited[num]=true;
//                 mapping[curr-'a']=num;
//                 if (wordBreak(unique,a,b,c,idx + 1,mapping,visited) == true) {
//                     return true;
//                 }
//                 visited[num]=false;
//                 mapping[curr-'a']=-1;
//             }
//         }
//         return false;
//     }

//     public static void main(String[] args) {
//         //send + more = money
//         String a="hello";
//         String b="world";
//         String c="progm";
//         String z=a+b+c;
//         String unique="";
//         HashSet<Character>hs=new HashSet<>();
//         for (int i = 0; i < z.length(); i++) {
//             if(hs.contains(z.charAt(i))==false)
//             {
//                 unique+=z.charAt(i);
//             }
//             hs.add(z.charAt(i));
//         }
//         System.out.println(unique);
//         int[] mapping=new int[26];
//         Arrays.fill(mapping, -1);
//         boolean[] visited=new boolean[10];
//         System.out.println(wordBreak(unique,a,b,c,0,mapping,visited));
//         for (int i = 0; i < mapping.length; i++) {
//             System.out.print((char)(i+'a')+":"+mapping[i]+" ");
//         }
//     }
// }

    //========== Sir's Approach(Exact Same but different coding style)===============//

//     static String str1 = "send", str2 = "more", str3 = "money";
//     static boolean[] isNumUsed = new boolean[10];
//     static int[] mapping = new int[26];

//     public static void crypto() {
//         String str = str1 + str2 + str3;
//         int[] freq = new int[26];
//         for (int i = 0; i < str.length(); i++) {
//             freq[str.charAt(i) - 'a']++;
//         }

//         str = "";
//         for (int i = 0; i < 26; i++) {
//             if (freq[i] > 0)
//                 str += (char) (i + 'a');
//         }

//         if (str.length() > 10)
//             return;

//         System.out.println(crypto(str, 0));
//     }

//     private static int stringToNumber(String str) {
//         int res = 0;
//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             res = res * 10 + mapping[ch - 'a'];
//         }

//         return res;
//     }

//     private static boolean isValidMapping() {
//         if (mapping[str1.charAt(0) - 'a'] == 0 || mapping[str2.charAt(0) - 'a'] == 0
//                 || mapping[str3.charAt(0) - 'a'] == 0)
//             return false;

//         int num1 = stringToNumber(str1);
//         int num2 = stringToNumber(str2);
//         int num3 = stringToNumber(str3);

//         return num1 + num2 == num3;
//     }

//     public static int crypto(String str, int idx) {
//         if (idx == str.length()) {
//             if (isValidMapping()) {
//                 for (int i = 0; i < str.length(); i++) {
//                     char ch = str.charAt(i);
//                     System.out.print(ch + "=" + mapping[ch - 'a'] + ", ");
//                 }
//                 System.out.println();
//                 return 1;
//             }

//             return 0;
//         }

//         int count = 0;
//         char ch = str.charAt(idx);
//         for (int num = 0; num <= 9; num++) {
//             if (!isNumUsed[num]) {
//                 isNumUsed[num] = true;
//                 mapping[ch - 'a'] = num;

//                 count += crypto(str, idx + 1);

//                 mapping[ch - 'a'] = 0;
//                 isNumUsed[num] = false;
//             }

//         }

//         return count;
//     }

//     public static void main(String... args) {
//         crypto();
//     }

// }