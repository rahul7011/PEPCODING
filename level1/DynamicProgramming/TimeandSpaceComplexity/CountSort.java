import java.util.Scanner;

public class CountSort {
    public static void countSort(int[] arr, int min, int max) {
        //determine range
        int[] farr=new int[max-min+1];
        //fill frequency array
        for(int i=0;i<arr.length;i++)
        {
            farr[arr[i]-min]++;
        }
        //form prefix sum array
        int sum=0;
        for(int i=0;i<farr.length;i++)
        {
            sum+=farr[i];
            farr[i]=sum;
        }
        //Now forming answer array
        int[] finalarr=new int[arr.length];
        for(int i=arr.length-1;i>=0;i--)
        {
            int position=farr[arr[i]-min];
            finalarr[position-1]=arr[i];
            farr[arr[i]-min]--;
        }
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=finalarr[i];
        }
       }
     
       public static void print(int[] arr) {
         for (int i = 0; i < arr.length; i++) {
           System.out.println(arr[i]);
         }
       }
     
       public static void main(String[] args) throws Exception {
         Scanner scn = new Scanner(System.in);
         int n = scn.nextInt();
         int[] arr = new int[n];
         int max = Integer.MIN_VALUE;
         int min = Integer.MAX_VALUE;
         for (int i = 0; i < n; i++) {
           arr[i] = scn.nextInt();
           max = Math.max(max, arr[i]);
           min = Math.min(min, arr[i]);
         }
         countSort(arr,min,max);
         print(arr);
         scn.close();
       }
     
     }