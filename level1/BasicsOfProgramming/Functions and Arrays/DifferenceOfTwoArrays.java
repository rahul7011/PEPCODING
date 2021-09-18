import java.util.Scanner;

public class DifferenceOfTwoArrays {
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
        getArrDiff(arr1,arr2);
        scn.close();
    }
    public static void getArrDiff(int[] arr1,int[] arr2)
    {
        int[] finalarr=new int[arr2.length];
        int i=arr1.length-1;
        int j=arr2.length-1;
        int k=finalarr.length-1;
        int rem1,rem2,borrow=0;
        while(k>=0)
        {
            rem1=0;
            rem2=0;
            if(i>=0)
            {
                rem1=arr1[i];
            }
            if(j>=0)
            {
                //this check is not required because the value of j and k 
                //will be equal in this case
                rem2=arr2[j];
            }
            int subrem=rem2-borrow-rem1;
            if(subrem<0)
            {
                subrem+=10;
                borrow=1;
            }
            else
            {
                //reset the borrow
                borrow=0;
            }
            finalarr[k]=subrem;
            k--;
            j--;
            i--;
        }
        i=0;
        while(finalarr[i]==0)
        {
            i++;
        }
        while(i<finalarr.length)
        {
            System.out.println(finalarr[i]);
            i++;
        }
    }
}
