import java.util.Scanner;

public class BarChart {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=scn.nextInt();
        }
        printBar(arr);
        scn.close();
    }
    public static void printBar(int[] arr)
    {
        //finding the max
        int max=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            if(max<arr[i])
            max=arr[i];
        }
        //printing the bar chart
        for(int i=max;i>=1;i--)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(arr[j]>=i)
                {
                    System.out.print("*\t");
                }
                else
                {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}
