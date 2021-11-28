import java.util.Arrays;
import java.util.Scanner;

public class TargetSumPairWithBinarySearch {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int target=scn.nextInt();
        checkTargetSumBS(arr,target);
        scn.close();
    }
    //O(nlogn)
    public static void checkTargetSumBS(int[] arr,int target)
    {
        Arrays.sort(arr);   //takes O(nlogn)
        for (int i = 0; i < arr.length; i++) {  //O(n)
            int newtarget=target-arr[i];
            

            int low=i+1;    //To avoid printing of redundant pairs like (a,b) and (b,a)
                            //i.e, We are finding the new target on the right side of the current element                  
            int high=arr.length-1;
            while(low<=high)    //O(logn) for the binary search
            {
                int mid = low + ((high - low + 1) / 2);
                if(arr[mid]==newtarget)
                {
                    System.out.println(arr[i]+", "+arr[mid]);
                    break;
                }
                else if(arr[mid]<newtarget)
                {
                    low=mid+1;
                }
                else
                {
                    high=mid-1;
                }
            }
        }
        //t(n)=t(nlogn)+t(nlogn)
        //-> O(nlogn)
    }
}
