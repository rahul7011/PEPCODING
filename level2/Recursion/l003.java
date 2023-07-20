import java.util.ArrayList;

public class l003 {

    public static void display1(int[] a) {
        for (int x : a) {
            System.out.println(x + " ");
        }
    }

    public static void display2(int[][] a) {
        for (int[] x : a) {
            display1(x);
        }
    }

    // very important most people unable to recognize this case
    public static int mazePath_shortest(int sr, int sc, int[][] mat, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {
        int n = mat.length - 1;
        int m = mat[0].length - 1;
        if (sr == n && sc == m) {
            return 0;
        }
        int jump = mat[sr][sc];
        mat[sr][sc] = 0;
        int minPath = (int) 1e9; // this is acting as a marker
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= jump; rad++) {
                int x = sr + rad * dir[d][0];
                int y = sc + rad * dir[d][1];
                if (x >= 0 && y >= 0 && x <= n && y <= m) {
                    if (mat[x][y] != 0) {
                        int recAns = mazePath_shortest(x, y, mat, dir, dirS, ans, psf + dirS[d]);
                        if (recAns != (int) 1e9) {
                            // if we do not include this condition then it will find ans even if ans does
                            // not exist
                            minPath = Math.min(minPath, recAns + 1);
                        }
                    }
                } else {
                    break;
                }
            }
        }
        mat[sr][sc] = jump;
        return minPath;
    }

    // Good Question(Amazing intution)
    public static int equalSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += equalSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2);
        count += equalSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");
        return count;
    }

    public static void equal() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        equalSet(arr, 1, arr[0], 0, arr[0] + " ", "");
    }

    // https://leetcode.com/problems/partition-equal-subset-sum/
    class Solution {
        public boolean solve(int[] nums, int idx, int sum1, int sum2) {
            if (idx == nums.length) {
                return sum1 == sum2;
            }
            boolean a1 = solve(nums, idx + 1, sum1 + nums[idx], sum2);
            boolean a2 = solve(nums, idx + 1, sum1, sum2 + nums[idx]);
            return a1 || a2;
        }

        public boolean canPartition(int[] nums) {
            return solve(nums, 0, 0, 0);
        }
    }

    // permuatation
    public static int permute(String s, String ans) {
        if (s.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String rem = s.substring(0, i) + s.substring(i + 1);
            count += permute(rem, ans + ch);
        }
        return count;
    }

    public static void permuteCall() {
        //  each permutation will be printed (unique and non-unique as well)
        // System.out.println(permute("aaab", ""));

        //  only unique permutation will be printed

        //  does not reuires sorted input but uses extra space
        System.out.println(permuteUnique("aaab", ""));

        //does require sorting(sorted input) but does not uses extra space
        System.out.println(permuteUnique2("aaab", ""));
    }

    // for getting all the unique permutations
    public static int permuteUnique(String s, String ans) {
        //This method does not requires sorting(sorted string as input) but it does require extra space(which can also reduced with bits )
        if(s.length()==0)
        {
            System.out.println(ans);
            return 1;
        }
        boolean[] map = new boolean[26];
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map[ch-'a'] == false) {
                map[ch-'a']=true;
                String rem = s.substring(0, i) + s.substring(i + 1);
                count += permuteUnique(rem, ans + ch);
            }
        }
        return count;
    }

    //This method does requires sorting(sorted input) but it does not require extra space
    public static int permuteUnique2(String s, String ans) {
        if(s.length()==0)
        {
            System.out.println(ans);
            return 1;
        }
        char prev='#';  //initially
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (prev!=ch) {
                String rem = s.substring(0, i) + s.substring(i + 1);
                count += permuteUnique(rem, ans + ch);
            }
            prev=ch;
        }
        return count;
    }

    public static void main(String[] args) {
        // int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        // String[] dirS = { "Up", "Right", "Down", "Left" };
        // int[][] mat = { { 2, 1, 0, 0 }, { 3, 0, 0, 1 }, { 0, 1, 0, 1 }, { 0, 0, 0, 1
        // } };
        // int[][] mat = { { 1, 1 }, { 1, 1 } };
        // ArrayList<String> res = new ArrayList<>();
        // System.out.println(mazePath_shortest(0, 0, mat, dir, dirS, res, ""));
        equal();
        // permuteCall();
    }
}
