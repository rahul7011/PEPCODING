package Recursion;
public class l1 {
    public static int mazePath(int sr, int sc, int er, int ec, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec) {
            count += mazePath(sr, sc + 1, er, ec, psf + "h");
        }
        if (sr + 1 <= er) {
            count += mazePath(sr + 1, sc, er, ec, psf + "v");
        }
        if (sr + 1 <= er && sc + 1 <= ec) {
            count += mazePath(sr + 1, sc + 1, er, ec, psf + "d");
        }
        return count;
    }

    public static int mazePath2(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath2(r, c, er, ec, dir, dirS, psf + dirS[d]);
            }
        }
        return count;
    }

    public static int mazePath3(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        // direction fix and radius variable to avoid outside boundary calls(excess
        // calls)
        for (int d = 0; d < dir.length; d++) {
            for (int radius = 1; radius <= Math.max(er, ec); radius++) {
                int r = sr + radius * dir[d][0];
                int c = sc + radius * dir[d][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePath3(r, c, er, ec, dir, dirS, psf + dirS[d] + radius);
                }
            }
        }
        return count;
    }

    public static int floodfill(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf,
            boolean[][] visited) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        visited[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && visited[r][c] == false) {
                count += floodfill(r, c, er, ec, dir, dirS, psf + dirS[d], visited);
            }
        }
        visited[sr][sc] = false;
        return count;
    }
    public static int floodfillWithJump(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf,
            boolean[][] visited) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        visited[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            for (int radius = 1; radius <= Math.max(er, ec); radius++) {                
                int r = sr + radius*dir[d][0];
                int c = sc + radius*dir[d][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec && visited[r][c] == false) {
                    count += floodfillWithJump(r, c, er, ec, dir, dirS, psf + dirS[d]+radius, visited);
                }
            }
        }
        visited[sr][sc] = false;
        return count;
    }
    
    //leetcode 62

    // class Solution {
    //     public static int mazePath(int sr,int sc,int er,int ec,int[][] dir)
    //     {
    //         if(sr==er&&sc==ec)
    //         {
    //             return 1;
    //         }
    //         int count=0;
    //         for(int d=0;d<dir.length;d++)
    //         {
    //             int r=sr+dir[d][0];
    //             int c=sc+dir[d][1];
    //             if(r>=0&&c>=0&&r<=er&&c<=ec)
    //             {
    //                 count+=mazePath(r,c,er,ec,dir);
    //             }
    //         }
    //         return count;
    //     }
    //     public int uniquePaths(int m, int n) {
    //         int[][] dir={{0,1},{1,0}};
    //         return mazePath(0,0,m-1,n-1,dir);
    //     }
    // }

    //leetcode 63

    // class Solution {
    //     public static int mazePath(int[][] obstacleGrid,int sr,int sc,int er,int ec,int[][] dir)
    //         {
    //             if(sr==er&&sc==ec)
    //             {   
    //                 return 1;
    //             }
    //             int count=0;
    //             for(int d=0;d<dir.length;d++)
    //             {
    //                 int r=sr+dir[d][0];
    //                 int c=sc+dir[d][1];
    //                 if(r>=0&&c>=0&&r<=er&&c<=ec&&obstacleGrid[r][c]!=1)
    //                 {
    //                     count+=mazePath(obstacleGrid,r,c,er,ec,dir);
    //                 }
    //             }
    //             return count;
    //         }
    //     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    //         int[][] dir={{0,1},{1,0}};
    //         int m=obstacleGrid.length;
    //         int n=obstacleGrid[0].length;
    //         return obstacleGrid[0][0]!=1?mazePath(obstacleGrid,0,0,m-1,n-1,dir):0;
    //     }
    // }
    public static String floodfillWithJumpShortest(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf,
        boolean[][] visited) {
        if (sr == er && sc == ec) {
            return psf;
        }
        String shortest = "";
        visited[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            for (int radius = 1; radius <= Math.max(er, ec); radius++) {                
                int r = sr + radius*dir[d][0];
                int c = sc + radius*dir[d][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec && visited[r][c] == false) {
                    String candidate = floodfillWithJumpShortest(r, c, er, ec, dir, dirS, psf + dirS[d]+radius, visited);
                    if(shortest.length()==0||candidate.length()<shortest.length())
                    {
                        shortest=candidate;
                    }
                }
            }
        }
        visited[sr][sc] = false;
        return shortest;
    }
    public static String floodfillWithJumpLongest(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf,
        boolean[][] visited) {
        if (sr == er && sc == ec) {
            return psf;
        }
        String longest = "";
        visited[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            for (int radius = 1; radius <= Math.max(er, ec); radius++) {                
                int r = sr + radius*dir[d][0];
                int c = sc + radius*dir[d][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec && visited[r][c] == false) {
                    String candidate = floodfillWithJumpLongest(r, c, er, ec, dir, dirS, psf + dirS[d]+radius, visited);
                    if(candidate.length()>longest.length())
                    {
                        longest=candidate;
                    }
                }
            }
        }
        visited[sr][sc] = false;
        return longest;
    }

    //Better Approach using Pair class for avoid static variables
    public static class Pair{
        int len;
        String path;
        Pair(){
            this.len=(int)1e9;  //Avoid Integer.MAX_VALUE to avoid overflow
            this.path=null;
        }
        Pair(int len,String path){
            this.len=len;
            this.path=path;
        }
    }
    //better approach
    public static Pair floodfillWithJumpShortest2(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf,boolean[][] visited){
        if(sr==er&&sc==ec)
        {
            return (new Pair(psf.length(),psf));
        }
        visited[sr][sc]=true;
        Pair shortest=new Pair();
        for (int d = 0; d < dir.length; d++) {
            int r=sr+dir[d][0];
            int c=sc+dir[d][1];
            if(r>=0&&c>=0&&r<=er&&c<=ec&&visited[r][c]==false)
            {
                Pair candidate=floodfillWithJumpShortest2(r, c, er, ec, dir, dirS, psf+dirS[d], visited);
                if(candidate.len<shortest.len)
                {
                    shortest.len=candidate.len;
                    shortest.path=candidate.path;
                }
            }
        }
        visited[sr][sc]=false;
        return shortest;
    }

    public static void main(String[] args) {
        // System.out.println(mazePath(0, 0, 2, 2, ""));
        // int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        // String[] dirS = { "h", "v", "d" };
        // System.out.println(mazePath3(0, 0, 2, 2, dir, dirS, ""));
        int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        String[] dirS = { "r", "w", "d", "n", "l", "s", "u", "e" };
        boolean[][] visited = new boolean[3][3];
        // System.out.println(floodfill(0, 0, 2, 2, dir, dirS, "", visited));
        // System.out.println(floodfillWithJump(0, 0, 2, 2, dir, dirS, "", visited));
        
        String shortest=floodfillWithJumpShortest(0, 0, 2, 2, dir, dirS, "", visited);
        System.out.println("Shortest Path:"+shortest+" "+shortest.length());
        Pair shortest2=floodfillWithJumpShortest2(0, 0, 2, 2, dir, dirS, "", visited);
        System.out.println("Shortest Path:"+shortest2.path+" "+shortest2.len);
        String longest=floodfillWithJumpLongest(0, 0, 2, 2, dir, dirS, "", visited);
        System.out.println("Longest Path:"+longest+" "+longest.length());
    }
}



//Homework

// https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
// class Solution {
//     public static ArrayList<String> floodfill(int[][] mat,int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf) {
//         if (sr == er && sc == ec) {
//             ArrayList<String>temp=new ArrayList<>();
//             temp.add(psf);
//             return temp;
//         }
//         ArrayList<String> list = new ArrayList<>();
//         mat[sr][sc] = 0;
//         for (int d = 0; d < dir.length; d++) {
//             int r = sr + dir[d][0];
//             int c = sc + dir[d][1];
//             if (r >= 0 && c >= 0 && r <= er && c <= ec && mat[r][c] == 1) {
//                 ArrayList<String>others=floodfill(mat,r, c, er, ec, dir, dirS, psf + dirS[d]);
//                 list.addAll(others);
//             }
//         }
//         mat[sr][sc] = 1;
//         return list;
//     }
//     public static ArrayList<String> findPath(int[][] mat, int N) {
//         int m=mat.length;
//         int n=mat[0].length;
//         int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
//         String[] dirS={"U","D","L","R"};
//         return mat[0][0]!=0?floodfill(mat,0,0,m-1,n-1,dir,dirS,""):new ArrayList<>();
//     }
// }

