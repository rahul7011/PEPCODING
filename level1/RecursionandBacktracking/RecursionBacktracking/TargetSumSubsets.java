import java.util.Scanner;

public class TargetSumSubsets {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int target=scn.nextInt();
        printTargetSumSubsets(arr,0,"",0,target);
        scn.close();
    }
    //ssf->Subset So Far
    //sssf->Subset Sum So Far
    public static void printTargetSumSubsets(int[] arr,int idx,String ssf,int sssf,int target)
    {
        if(sssf==target && idx==arr.length)
        {
            System.out.println(ssf+".");
            return;
        }
        if(idx>=arr.length || sssf>target)
        {
            return;
        }
        //Including call
        printTargetSumSubsets(arr, idx+1, ssf+arr[idx]+", ", sssf+arr[idx], target);
        //Excluding call
        printTargetSumSubsets(arr, idx+1, ssf, sssf, target);
    }
}
