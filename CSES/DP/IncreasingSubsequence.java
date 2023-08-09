import java.util.*;

public class IncreasingSubsequence {

    // //O(n2) this sucks
    // private static int solve(int[] arr,int[] dp) {
    // int max=0;
    // for (int i = 0; i < arr.length; i++) {
    // int cnt = 1;
    // for (int j = i - 1; j >= 0; j--) {
    // if (arr[i] > arr[j])
    // cnt = Math.max(cnt, dp[j] + 1);
    // }
    // dp[i]=cnt;
    // max=Math.max(max,cnt);
    // }
    // // System.out.println(Arrays.toString(dp));
    // return max;
    // }

    private static int lower_bound(List<Integer> nums, int target) {
        int low = 0, high = nums.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums.get(mid) < target)
                low = mid + 1;
            else
                high = mid;
        }
        if (low < nums.size() && nums.get(low) < target)
            return low + 1;
        return low;
    }

    // O(nlogn) now we are talking
    private static int optimised(int[] arr, int[] dp) {
        List<Integer> lis = new ArrayList<>();
        lis.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (lis.get(lis.size() - 1) < arr[i]) {
                lis.add(arr[i]);
            } else {
                int idx = lower_bound(lis, arr[i]);
                lis.set(idx, arr[i]);
            }
        }
        return lis.size();
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int[] dp = new int[n];
        // System.out.println(solve(arr, dp));
        System.out.println(optimised(arr, dp));
    }
}
