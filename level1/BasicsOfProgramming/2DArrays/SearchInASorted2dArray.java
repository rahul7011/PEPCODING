import java.util.Scanner;

public class SearchInASorted2dArray {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[][] arr=new int[n][n];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                arr[i][j]=scn.nextInt();
            }
        }
        int x=scn.nextInt();
        searchIn2DArray(arr,x);
        scn.close();
    }
    public static void searchIn2DArray(int[][] arr,int x)
    {
        int i=0;
        int j=arr[0].length-1;
        while(i<arr.length && j>=0)
        {
            if(arr[i][j]<x)
            {
                i++;
            }
            else if(arr[i][j]>x)
            {
                j--;
            }
            else
            {
                System.out.print(i+"\n"+j);
                return;
            }
        }
        System.out.print("Not Found");
    }
}
