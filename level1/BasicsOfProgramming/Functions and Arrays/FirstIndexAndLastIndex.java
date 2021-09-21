import java.util.Scanner;

public class FirstIndexAndLastIndex {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=scn.nextInt();
        }
        int d=scn.nextInt();
        findIntervals(arr,d);
        scn.close();
    }
    public static void findIntervals(int[] arr,int find)
    {
        int low=0;
        int high=arr.length-1;
        int hi=-1;
        int lo=-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(arr[mid]<find)
            {
                low=mid+1;
            }
            else if(arr[mid]>find)
            {
                high=mid-1;
            }
            else
            {
                hi=mid;
                high=mid-1;
            }
        }
        low=0;
        high=arr.length-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(arr[mid]<find)
            {
                low=mid+1;
            }
            else if(arr[mid]>find)
            {
                high=mid-1;
            }
            else
            {
                lo=mid;
                low=mid+1;
            }
        }
        System.out.println(hi);
        System.out.println(lo);
    }
}
