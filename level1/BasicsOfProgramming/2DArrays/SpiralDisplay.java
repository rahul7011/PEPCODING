import java.util.Scanner;

public class SpiralDisplay {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n + 1][m + 1]; //1 based indexing
    
        for (int i = 1; i <= n; i++)
        {
          for (int j = 1; j <= m; j++)
          {
            arr[i][j] = scn.nextInt();
          }
        }
        //spiral print(1-based indexing used here)
        int minr = 1;
        int maxr = n;
        int minc = 1;
        int maxc = m;
        int total = m * n;
        int count = 1;
        while (count <= total)
        {
          //Downwards
          for (int i = minr; i <= maxr && count <= total; i++)
          {
            System.out.println(arr[i][minc]);
            count++;
          }
          minc++;
          //Rightwards
          for (int i = minc; i <= maxc && count <= total; i++)
          {
            System.out.println(arr[maxr][i]);
            count++;
          }
          maxr--;
          //Upwards
          for (int i = maxr; i >= minr && count <= total; i--)
          {
            System.out.println(arr[i][maxc]);
            count++;
          }
          maxc--;
          //Leftwards
          for (int i = maxc; i >= minc && count <= total; i--)
          {
            System.out.println(arr[minr][i]);
            count++;
          }
          minr++;
        }
        scn.close();
      }
    }
    