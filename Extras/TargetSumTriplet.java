import java.util.Arrays;
import java.util.Scanner;
public class TargetSumTriplet {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int target=scn.nextInt();
        TarSumTriplet(arr,target);
        scn.close();
    }
    //complexity=O(n^2)
    public static void TarSumTriplet(int[] arr,int target)
    {
        Arrays.sort(arr);//O(nlogn)
        for (int i = 0; i < arr.length; i++) {  //O(n^2)
            int newtarget=target-arr[i];
            int low=i+1;
            int high=arr.length-1;
            while(low<high)
            {
                if(arr[low]+arr[high]==newtarget)
                {
                    System.out.println(arr[i]+","+arr[low]+", "+arr[high]);
                    low++;
                    high--;
                }else if(arr[low]+arr[high]<newtarget)
                {
                    low++;
                }else
                {
                    high--;
                }
            }
        }
    }
}
