import java.util.Scanner;

public class RadixSort {
    public static void radixSort(int[] arr) {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            if(max<arr[i])
            {
                max=arr[i];
            }
        }  
        int exp=1;
        while(exp<=max)
        {
            countSort(arr, exp);
            exp*=10;
        }
      }
    
      public static void countSort(int[] arr, int exp) {
        //determine range,Here this will be 10 as 0-9 is the range for a single digit number
        int[] farr=new int[10];
        //forming the frequency array
        for(int i=0;i<arr.length;i++)
        {
            farr[arr[i]/exp%10]++;
        }
        //forming the prefix sum array
        int sum=0;
        for(int i=0;i<farr.length;i++)
        {
            sum+=farr[i];
            farr[i]=sum;
        }
        //forming the final answer array
        int[] finalarray=new int[arr.length];
        for(int i=arr.length-1;i>=0;i--)
        {
            int position=farr[arr[i]/exp%10];
            finalarray[position-1]=arr[i];
            farr[arr[i]/exp%10]--;
        }
        //filling the original array
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=finalarray[i];
        }

        System.out.print("After sorting on " + exp + " place -> ");
        print(arr);
      }
    
      public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
          System.out.print(arr[i] + " ");
        }
        System.out.println();
      }
    
      public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = scn.nextInt();
        }
        radixSort(arr);
        print(arr);
        scn.close();
      }
    
    }
