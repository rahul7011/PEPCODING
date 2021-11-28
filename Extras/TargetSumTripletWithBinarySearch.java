import java.util.Arrays;
import java.util.Scanner;

public class TargetSumTripletWithBinarySearch {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int target=scn.nextInt();
        TsumTripleWithBS(arr,target);
        scn.close();
    }
    //Complexity-O(n^2logn)
    public static void TsumTripleWithBS(int[] arr,int target)
    {
        Arrays.sort(arr);   //O(nlogn)
        for (int i = 0; i < arr.length; i++) {  //(n^2logn)
            int newTarget=target-arr[i];
            for (int j = i+1; j < arr.length; j++) {
                int finalTarget=newTarget-arr[j];
                int low=j+1;
                int high=arr.length-1;
                while(low<=high)
                {
                    int mid=(low+high)/2;
                    if(arr[mid]==finalTarget)
                    {
                        System.out.println(arr[i]+", "+arr[j]+", "+arr[mid]);
                        break;
                    }
                    else if(arr[mid]<finalTarget)
                    {
                        low=mid+1;
                    }
                    else
                    {
                        high=mid-1;
                    }
                }
            }
        }
    }
}
// 11 
// 1 9 6 4 8 3 12 14 24 10 15
// 25
// 1,9, 15
// 1,10, 14
// 3,8, 14
// 3,10, 12
// 4,6, 15
// 4,9, 12
// 6,9, 10