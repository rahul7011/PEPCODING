import java.util.Scanner;

public class NQueens {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        boolean[][] chess=new boolean[n][n];
        printNQueens(chess,0,"");
        scn.close();
    }
    public static void printNQueens(boolean[][] chess,int row,String qsf)
    {
        if(row==chess.length)
        {
            System.out.println(qsf+".");
            return;
        }
        for (int col = 0; col < chess[0].length; col++) {
            if(IsItSafe(chess,row,col)==true)
            {
                chess[row][col]=true;
                printNQueens(chess, row+1, qsf+row+"-"+col+", ");
                chess[row][col]=false;
            }
        }
    }
    public static boolean IsItSafe(boolean[][] chess,int row,int col)
    {
        //upward column
        for (int i = row-1; i >= 0; i--) {
            if(chess[i][col]==true)
            {
                return false;
            }
        }
        //left diagonal
        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--)
        {
            if(chess[i][j]==true)
            {
                return false;
            }
        }
        //right diagonal
        for(int i=row-1,j=col+1;i>=0 && j<chess[0].length;i--,j++)
        {
            if(chess[i][j]==true)
            {
                return false;
            }
        }
        return true;
    }
}
