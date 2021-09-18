import java.util.Scanner;

public class SumOfTwoArrays {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n1=scn.nextInt();
        int[] arr1=new int[n1];
        for(int i=0;i<n1;i++)
        {
            arr1[i]=scn.nextInt();
        }
        int n2=scn.nextInt();
        int[] arr2=new int[n2];
        for(int i=0;i<n2;i++)
        {
            arr2[i]=scn.nextInt();
        }
        getArrSum(arr1,arr2);
        scn.close();
    }
    public static void getArrSum(int[] arr1,int[] arr2)
    {
        int n1=arr1.length;
        int n2=arr2.length;
        int[] finalarr=new int[n1>n2?n1:n2];
        int i=arr1.length-1;
        int j=arr2.length-1;
        int k=finalarr.length-1;
        int rem1,rem2,carry=0;
        while(k>=0)
        {
            rem1=0;
            rem2=0;
            //Inorder to access values safely
            if(i>=0)
            {
                rem1=arr1[i];
            }
            if(j>=0)
            {
                rem2=arr2[j];
            }
            int subrem=rem1+rem2+carry;
            carry=subrem/10;
            int subres=subrem%10;
            finalarr[k]=subres;
            k--;
            i--;
            j--;
        }
        if(carry>0)
        {
            System.out.println(carry);
        }
        for(i=0;i<finalarr.length;i++)
        {
            System.out.println(finalarr[i]);
        }

    }
}
