import java.util.Scanner;

public class PivotInSortedAndRotatedArray {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int pivot=findPivot(arr);
        System.out.println(pivot);
        scn.close();
    }
    public static int findPivot(int[] arr)
    {
        int low=0;
        int high=arr.length-1;
        //why low<high because a can only be possible if there are 2 or more than 2 elements
        while(low<high)
        {
            int mid=(low+high)/2;
            if(arr[mid]>arr[high])
            {
                //pivot is on the right side
                low=mid+1;
            }
            else
            {
                //pivot is on the left side
                high=mid;
            }
        }
        return arr[high];
    }
}
