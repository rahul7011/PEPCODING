import java.util.Scanner;

public class FloodFill {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int m=scn.nextInt();
        int[][] arr=new int[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j]=scn.nextInt();
            }
        }
        boolean[][] visited=new boolean[n][m];
        floodfill(arr, 0, 0, "",visited);
        scn.close();
    }
    public static void floodfill(int[][] maze,int sr,int sc,String asf,boolean[][] visited)
    {
        if(sr<0||sr>=maze.length||sc<0||sc>=maze[0].length||maze[sr][sc]==1||visited[sr][sc]==true)
        {
            return;
        }
        else if(sr==maze.length-1 && sc==maze[0].length-1)
        {
            System.out.println(asf);
            return;
        }
        //Top
        visited[sr][sc]=true;
        floodfill(maze, sr-1, sc, asf+"t",visited);
        //Left
        floodfill(maze, sr, sc-1, asf+"l", visited);
        //Down
        floodfill(maze, sr+1, sc, asf+"d", visited);
        //Right
        floodfill(maze, sr, sc+1, asf+"r", visited);
        visited[sr][sc]=false;
    }

}
