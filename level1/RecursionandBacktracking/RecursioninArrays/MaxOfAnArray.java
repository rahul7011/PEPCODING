import java.util.Scanner;

public class MaxOfAnArray {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int max=findMax(arr,0);
        System.out.println(max);
        scn.close();
    }
    public static int findMax(int[] arr,int idx)
    {
        if(idx>=arr.length)
        {
            return Integer.MIN_VALUE;
        }
        int tmax=findMax(arr, idx+1);
        if(tmax<arr[idx])
        {
            tmax=arr[idx];
        }
        return tmax;
    }
}
