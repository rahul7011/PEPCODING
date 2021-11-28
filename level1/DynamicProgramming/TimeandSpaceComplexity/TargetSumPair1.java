import java.util.Arrays;
import java.util.Scanner;

public class TargetSumPair1 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int target=scn.nextInt();
        targetSumPair(arr,target);
        scn.close();
    }
    //Constraints
    //given an array(arr) of distinct integers and a target.
    //Approach=Two Pointer O(nlogn)
    //we can also use the hasmap to find all the pairs in O(n) but then the space will O(n) aswell
    public static void targetSumPair(int[] arr,int target)
    {
        Arrays.sort(arr);   //takes O(nlogn)
        int low=0;
        int high=arr.length-1;
        while(low<high)     //takes O(n)
        {
            if(arr[low]+arr[high]==target)
            {
                System.out.println(arr[low]+", "+arr[high]);
                low++;
                high--;
            }
            else if(arr[low]+arr[high]<target)
            {
                low++;
            }
            else
            {
                high--;
            }
        }
    }
}
