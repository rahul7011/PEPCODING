import java.util.*;

public class moneySums {
    private static boolean solve(int[] arr, int idx, int sum, Boolean[][] dp) {
        if(idx==arr.length)
        {
            return dp[idx][sum]=true;
        }
        if(dp[idx][sum]!=null)
        {
            return dp[idx][sum];
        }
        boolean check1=false,check2=false;
        check1=solve(arr,idx+1,sum+arr[idx],dp);
        check2=solve(arr,idx+1,sum,dp);
        return dp[idx][sum]=check1||check2;

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
            x += arr[i];
        }
        Boolean[][] dp = new Boolean[n+1][x + 1];
        solve(arr, 0, 0, dp);
        int size=0;
        for(int i=1;i<=x;i++)
        {
            if(dp[n][i]!=null && dp[n][i]==true){
                size++;
            }
        }
        System.out.println(size);
        for(int i=1;i<=x;i++)
        {
            if(dp[n][i]!=null && dp[n][i]==true){
                System.out.print(i+" ");
            }
        }
    }
}