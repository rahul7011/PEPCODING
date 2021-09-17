import java.util.Scanner;

public class FindElementInAnArray {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int d = scn.nextInt();
        int elementAt = findPlace(arr, d);
        System.out.println(elementAt);
        scn.close();
    }
    public static int findPlace(int[] arr,int d)
    {
        int position=-1;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==d)
            {
                position=i;
                break;
            }
        }
        return position;
    }
}
