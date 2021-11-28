import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=scn.nextInt();
        }
        int[] merged=mergeSort(arr,0,arr.length-1);
        for(int val:merged)
        {
            System.out.print(val+" ");
        }
        scn.close();
    }
    public static int[] mergeSort(int[] arr,int lo,int hi)
    {
        if(lo==hi)
        {
            int[] temp=new int[1];
            temp[0]=arr[lo];
            return temp;
        }
        int mid=(lo+hi)/2;
        int[] firstSortedHalf=mergeSort(arr, lo, mid);
        int[] secondSortedHalf=mergeSort(arr, mid+1, hi);
        int[] finalSortedArray=merge2SortedArray(firstSortedHalf,secondSortedHalf);
        return finalSortedArray;
    }
    public static int[] merge2SortedArray(int[] a,int[] b)
    {
        int i=0;
        int j=0;
        int k=0;
        int[] finalSortedArray=new int[a.length+b.length];
        while(i<a.length&&j<b.length)
        {
            if(a[i]<b[j])
            {
                finalSortedArray[k]=a[i];
                i++;
                k++;
            }
            else
            {
                finalSortedArray[k]=b[j];
                j++;
                k++;
            }
        }
        while(i<a.length)
        {
            finalSortedArray[k]=a[i];
            i++;
            k++;
        }
        while(j<b.length)
        {
            finalSortedArray[k]=b[j];
            j++;
            k++;
        }
        return finalSortedArray;
    }
}
