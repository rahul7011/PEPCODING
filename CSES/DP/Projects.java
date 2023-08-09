import java.util.*;

public class Projects {
    // O(n2) not good
    // private static long solve(int[][] arr) {
    // Arrays.sort(arr, (a, b) -> {
    // return a[1] - b[1];
    // });
    // long max = 0;
    // long[] dp = new long[arr.length];
    // for (int i = 0; i < arr.length; i++) {
    // long cmax = arr[i][2];
    // for (int j = i - 1; j >= 0; j--) {
    // if (arr[i][0] > arr[j][1]) {
    // cmax = Math.max(cmax, dp[j] + arr[i][2]);
    // }
    // }
    // dp[i] = cmax;
    // max = Math.max(max, cmax);
    // }
    // return max;
    // }
    private static int lower_bound(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        if (low < nums.length && nums[low] < target)
            return low + 1;
        return low;
    }

    private static long solve(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });
        int n = arr.length;
        int[] deadline=new int[n];
        for (int i = 1; i < deadline.length; i++) {
            deadline[i-1]=arr[i][1];
        }
        long[] dp = new long[n+1];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            long op1 = dp[i - 1]; // nhi lena
            long op2 = arr[i][2]; // lena
            int idx=lower_bound(deadline, arr[i][0])+1;
            idx--;
            if(idx>=0)
            {
                op2+=dp[idx];
            }
            dp[i]=Math.max(op1,op2);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[][] arr = new int[n+1][3];
        arr[0]=new int[]{0,0,0};
        for (int i = 1; i < arr.length; i++) {
            int a = scn.nextInt();
            int b = scn.nextInt();
            int c = scn.nextInt();
            arr[i] = new int[] { a, b, c };
        }
        System.out.println(solve(arr));
    }
}
