
import java.util.ArrayList;
import java.util.Scanner;

public class l002 {

    // Top to Down(ans is forming on the way down)

    // Skip this one as it is making unnecessary calls and then checking whether the
    // call is right

    // public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec)
    // {
    // if (sr > er || sc > ec) {
    // return new ArrayList<>();
    // }
    // if (sr == er && sc == ec) {
    // ArrayList<String> base = new ArrayList<>();
    // base.add("");
    // return base;
    // }
    // ArrayList<String> finalList = new ArrayList<>();
    // ArrayList<String> hMoves = mazePath_HVD(sr, sc + 1, er, ec);
    // for (String s : hMoves)
    // finalList.add("h" + s);
    // ArrayList<String> vMoves = mazePath_HVD(sr + 1, sc, er, ec);
    // for (String s : vMoves)
    // finalList.add("v" + s);
    // ArrayList<String> dMoves = mazePath_HVD(sr + 1, sc + 1, er, ec);
    // for (String s : dMoves)
    // finalList.add("d" + s);
    // return finalList;
    // }

    // better version as it is not making any unnecessary calls
    public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> Vertical = mazePath_HVD(sr + 1, sc, er, ec);
            for (String s : Vertical) {
                myAns.add("V" + s);
            }
        }

        if (sc + 1 <= ec && sr + 1 <= er) {
            ArrayList<String> Diagonal = mazePath_HVD(sr + 1, sc + 1, er, ec);
            for (String s : Diagonal) {
                myAns.add("D" + s);
            }

        }

        if (sc + 1 <= ec) {
            ArrayList<String> Horizontal = mazePath_HVD(sr, sc + 1, er, ec);
            for (String s : Horizontal) {
                myAns.add("H" + s);
            }
        }

        return myAns;
    }

    // Bottom to Top(ans is forming on the way up)

    // Skip this one as it is making unnecessary calls and then checking whether the
    // call is right

    // public static int mazePath_HVD(int sr, int sc, int er, int ec,
    // ArrayList<String> ans, String psf) {
    // if (sr > er || sc > ec) {
    // return 0;
    // }
    // if (sr == er && sc == ec) {
    // ans.add(psf);
    // return 1;
    // }
    // int count = 0;
    // count += mazePath_HVD(sr, sc + 1, er, ec, ans, psf + "h");
    // count += mazePath_HVD(sr + 1, sc, er, ec, ans, psf + "v");
    // count += mazePath_HVD(sr + 1, sc + 1, er, ec, ans, psf + "d");
    // return count;
    // }

    // better version as it is not making any unnecessary calls
    public static int mazePath_HVD(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc, er, ec, ans, psf + "V");
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazePath_HVD(sr + 1, sc + 1, er, ec, ans, psf + "D");
        if (sc + 1 <= ec)
            count += mazePath_HVD(sr, sc + 1, er, ec, ans, psf + "H");

        return count;
    }

    // multi jumps
    public static ArrayList<String> mazePath_HVD_multi(int sr, int sc, int er, int ec) {
        if (sr > er || sc > ec) {
            return new ArrayList<>();
        }
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> finalList = new ArrayList<>();
        ArrayList<String> hMoves = new ArrayList<>();
        for (int i = 1; i <= (ec - sc); i++) {
            ArrayList<String> temp = mazePath_HVD_multi(sr, sc + i, er, ec);
            for (String s : temp)
                hMoves.add("h" + i + s);
        }
        finalList.addAll(hMoves);

        ArrayList<String> vMoves = new ArrayList<>();
        for (int i = 1; i <= (er - sr); i++) {
            ArrayList<String> temp = mazePath_HVD_multi(sr + i, sc, er, ec);
            for (String s : temp)
                vMoves.add("v" + i + s);
        }
        finalList.addAll(vMoves);

        ArrayList<String> dMoves = new ArrayList<>();
        for (int i = 1; i <= (er - sr) && i <= (ec - sc); i++) {
            ArrayList<String> temp = mazePath_HVD_multi(sr + i, sc + i, er, ec);
            for (String s : temp)
                dMoves.add("d" + i + s);
        }
        finalList.addAll(dMoves);

        return finalList;
    }

    // multi jumps but bottom to top
    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf) {
        if (sr > er || sc > ec) {
            return 0;
        }
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= (er - sr); i++) {
            count += mazePath_HVD_multi(sr + i, sc, er, ec, ans, psf + "v" + i);
        }
        for (int i = 1; i <= (ec - sc) && i <= (er - sr); i++) {
            count += mazePath_HVD_multi(sr + i, sc + i, er, ec, ans, psf + "d" + i);
        }
        for (int i = 1; i <= (ec - sc); i++) {
            count += mazePath_HVD_multi(sr, sc + i, er, ec, ans, psf + "h" + i);
        }
        return count;
    }

    // Above are those hard coded method we need some generic method to work with
    public static int mazePath_HVD2(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if (x >= 0 && y >= 0 && x <= er && y <= ec) {
                count += mazePath_HVD2(x, y, er, ec, dir, dirS, ans, psf + dirS[d]);
            }
        }
        return count;
    }

    // Now we are starting the backtracking part
    public static int floodfill(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {
        int n = vis.length - 1, m = vis[0].length - 1;
        if (sr == n && sc == m) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if (x >= 0 && y >= 0 && x <= n && y <= m && vis[x][y] == false) {
                count += floodfill(x, y, vis, dir, dirS, ans, psf + dirS[d]);
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodfill_mutli(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS,
            ArrayList<String> ans,
            String psf) {
        int n = vis.length - 1, m = vis[0].length - 1;
        if (sr == n && sc == m) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        vis[sr][sc] = true;
        // optimal usage of radius, we are only making correct calls(radius is varying
        // for each dir to stop excessive calls)
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int x = sr + rad * dir[d][0];
                int y = sc + rad * dir[d][1];
                if (x >= 0 && y >= 0 && x <= n && y <= m) {
                    if (vis[x][y] == false) {
                        count += floodfill_mutli(x, y, vis, dir, dirS, ans, psf + dirS[d] + rad);
                    } else {
                        break;
                    }

                }
            }
        }
        vis[sr][sc] = false;
        return count;
    }


    //https://practice.geeksforgeeks.org/problems/special-matrix4201/1#
    //Result: TLE
    class Solution {
        public int solve(int sr, int sc, int er, int ec, int[][] dir, boolean[][] marked) {
            if (sr == er && sc == ec) {
                return 1;
            }
            int count = 0;
            int mod = (int) 1e9 + 7;
            for (int d = 0; d < dir.length; d++) {
                int x = sr + dir[d][0];
                int y = sc + dir[d][1];
                if (x >= 0 && y >= 0 && x <= er && y <= ec && marked[x][y] == false) {
                    count = ((solve(x, y, er, ec, dir, marked) % mod) + (count % mod)) % mod;
                }
            }
            return count;
        }

        public int FindWays(int n, int m, int[][] blocked_cells) {
            boolean[][] marked = new boolean[n + 1][m + 1];
            for (int i = 0; i < blocked_cells.length; i++) {
                int r = blocked_cells[i][0];
                int c = blocked_cells[i][1];
                marked[r][c] = true;
            }
            int[][] dir = { { 0, 1 }, { 1, 0 } };
            return marked[1][1] != true ? solve(1, 1, n, m, dir, marked) : 0;
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // System.out.println(mazePath_HVD(0, 0, 3, 3));
        ArrayList<String> ans = new ArrayList<>();
        // System.out.println(mazePath_HVD(0, 0, 2, 2, ans, "") + " --> " + ans);
        // System.out.println(mazePath_HVD_multi(0, 0, 2, 2));
        // System.out.println(mazePath_HVD_multi(0, 0, 2, 2, ans, "") + " --> " + ans);
        // ans = new ArrayList<>();
        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        String[] dirS = { "V", "H", "D" };
        // System.out.println(mazePath_HVD2(0, 0, 2, 2, dir, dirS, ans, "")+" -> "+ans);
        // int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1},
        // { 0, -1 }, { -1, -1 } };
        // String[] dirS = { "U", "E", "L", "S", "D", "N", "R", "W" };
        ans = new ArrayList<>();
        boolean[][] vis = new boolean[3][3];
        // System.out.println(floodfill(0, 0, vis, dir, dirS, ans, "") + " -> " + ans);
        System.out.println(floodfill_mutli(0, 0, vis, dir, dirS, ans, "") + " -> " + ans);
        scn.close();
    }
}
