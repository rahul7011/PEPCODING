import java.util.Scanner;
public class KnightsTour {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int row=scn.nextInt();
        int col=scn.nextInt();
        int[][] chess=new int[n][n];
        printKnightsTour(chess,row,col,1);
        scn.close();
    }
    public static void printKnightsTour(int[][] chess,int row,int col,int moveNumber)
    {
        if(row<0||row>=chess.length||col<0||col>=chess[0].length||chess[row][col]!=0)
        {
            return;
        }
        if(moveNumber==chess.length*chess[0].length)
        {
            chess[row][col]=moveNumber;
            DisplayArray(chess);
            chess[row][col]=moveNumber;
            return;
        }
        chess[row][col]=moveNumber;
        printKnightsTour(chess, row-2, col+1, moveNumber+1);
        printKnightsTour(chess, row-1, col+2, moveNumber+1);
        printKnightsTour(chess, row+1, col+2, moveNumber+1);
        printKnightsTour(chess, row+2, col+1, moveNumber+1);
        printKnightsTour(chess, row+2, col-1, moveNumber+1);
        printKnightsTour(chess, row+1, col-2, moveNumber+1);
        printKnightsTour(chess, row-1, col-2, moveNumber+1);
        printKnightsTour(chess, row-2, col-1, moveNumber+1);
        chess[row][col]=0;
    }
    public static void DisplayArray(int[][] chess)
    {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
