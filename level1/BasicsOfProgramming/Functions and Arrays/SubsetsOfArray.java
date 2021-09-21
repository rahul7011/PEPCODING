import java.util.Scanner;

public class SubsetsOfArray {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        printSubset(arr);
        scn.close();
    }

    public static void printSubset(int[] arr) {
        String str="";  //blank string for the 
        int n = arr.length;
        int limit = (1 << n);
        for (int i = 0; i < limit; i++) {
            //print if rem is not zero
            str="";
            int decimal=i;
            for(int j=0;j<arr.length;j++)
            {
                int rem=decimal%2;
                decimal/=2;
                if(rem==0)
                {
                    //print -
                    str="-\t"+str; 
                }
                else
                {
                    //print the element from the last
                    str=arr[arr.length-1-j]+"\t"+str;
                }
            }
            System.out.println(str);
        }
    }
}
