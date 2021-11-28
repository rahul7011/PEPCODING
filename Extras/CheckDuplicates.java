import java.util.Arrays;
import java.util.Scanner;

public class CheckDuplicates {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        System.out.println(checkDuplicate(arr));
        scn.close();
    }
    //constraints -1 will never be given inside the arrays
    //only one duplicate item will be present
    //O(nlogn) and space O(1) no extra space
    //we can also create the hasmap in O(n) but then the space will O(n) aswell
    public static int checkDuplicate(int[] arr)
    {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]==arr[i+1])
            {
                return arr[i];
            }
        }
        return -1;
    }

}
