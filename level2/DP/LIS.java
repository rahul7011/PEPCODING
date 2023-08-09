import java.util.Arrays;

public class LIS {
    // 300. Longest Increasing Subsequence O(n2)
    class Solution {
        public static int longestIncreasingSubseq(int[] arr, int ei) {
            int max = 1;
            for (int i = ei - 1; i >= 0; i--) {
                if (arr[i] < arr[ei]) {
                    max = Math.max(max, longestIncreasingSubseq(arr, i) + 1);
                }
            }
            return max;
        }

        public static int longestIncreasingSubseq_memo(int[] arr, int ei, int[] dp) {
            if (dp[ei] != 0) {
                return dp[ei];
            }
            int max = 1;
            for (int i = ei - 1; i >= 0; i--) {
                if (arr[i] < arr[ei]) {
                    max = Math.max(max, longestIncreasingSubseq_memo(arr, i, dp) + 1);
                }
            }
            return dp[ei] = max;
        }

        public static int longestIncreasingSubseq_tabu(int[] arr, int[] dp) {
            for (int i = 0; i < arr.length; i++) {
                int max = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] < arr[i]) {
                        max = Math.max(max, dp[j] + 1);
                    }
                }
                dp[i] = max;
            }

            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max, dp[i]);
            }
            return max;
        }


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
    private static int LIS_optimised(int[] arr, int[] dp) {
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
        public int lengthOfLIS(int[] arr) {
            int[] dp = new int[arr.length];
            // int max=0;
            // for(int i=0;i<arr.length;i++)
            // {
            // // max=Math.max(max,longestIncreasingSubseq(arr, i));
            // max=Math.max(max,longestIncreasingSubseq_memo(arr, i,dp));
            // }
            // return max;
            // return longestIncreasingSubseq_tabu(arr, dp);
            return LIS_optimised(arr, dp);
        }
    }

    public static String longestIncreasingSubseq_backEngine(int[] arr, int[] dp, int idx) {
        int max = 0;
        int nidx = -1;
        for (int i = idx - 1; i >= 0; i--) {
            if (arr[i] < arr[idx]) {
                if (max < dp[i]) {
                    max = dp[i];
                    nidx = i;
                }
            }
        }
        return nidx == -1 ? arr[idx] + " " : longestIncreasingSubseq_backEngine(arr, dp, nidx) + arr[idx] + " ";
    }

    private static void LISCall() {
        // int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int[] arr = { 0, 1, 0, 3, 2, 3 };
        int[] dp = new int[arr.length];
        Solution.longestIncreasingSubseq_tabu(arr, dp);
        int idx = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < dp[i]) {
                max = dp[i];
                idx = i;
            }
        }
        System.out.println(longestIncreasingSubseq_backEngine(arr, dp, idx));
    }

    public static int longestDecreasingSubseq(int[] arr, int ei) {
        int max = 1;
        for (int i = ei - 1; i >= 0; i--) {
            if (arr[i] > arr[ei]) {
                max = Math.max(max, longestDecreasingSubseq(arr, i) + 1);
            }
        }
        return max;
    }

    public static int longestDecreasingSubseq_memo(int[] arr, int ei, int[] dp) {
        if (dp[ei] != 0) {
            return dp[ei];
        }
        int max = 1;
        for (int i = ei - 1; i >= 0; i--) {
            if (arr[i] > arr[ei]) {
                max = Math.max(max, longestDecreasingSubseq_memo(arr, i, dp) + 1);
            }
        }
        return dp[ei] = max;
    }

    public static int longestDecreasingSubseq_tabu(int[] arr, int[] dp) {
        for (int i = 0; i < arr.length; i++) {
            int max = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static String longestDecreasingSubseq_backEngine(int[] arr, int[] dp, int idx) {
        int max = 0;
        int nidx = -1;
        for (int i = idx - 1; i >= 0; i--) {
            if (arr[i] > arr[idx]) {
                if (max < dp[i]) {
                    max = dp[i];
                    nidx = i;
                }
            }
        }
        return nidx == -1 ? arr[idx] + " " : longestDecreasingSubseq_backEngine(arr, dp, nidx) + arr[idx] + " ";
    }

    private static void LDSCall() {
        // int[] arr={15, 27, 14, 38, 63, 55, 46, 65, 85};
        int[] arr = { 50, 3, 10, 7, 40, 80 };
        int[] dp = new int[arr.length];
        // int max=0;
        // for(int i=0;i<arr.length;i++)
        // {
        // // max=Math.max(max,longestDecreasingSubseq(arr, i));
        // max=Math.max(max,longestDecreasingSubseq_memo(arr, i,dp));
        // }
        // System.out.println(max);
        System.out.println(longestDecreasingSubseq_tabu(arr, dp));
        int idx = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < dp[i]) {
                max = dp[i];
                idx = i;
            }
        }
        System.out.println(longestDecreasingSubseq_backEngine(arr, dp, idx));
    }

    // Longest Bitonic subsequence
    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    class Solution1 {
        public static void longestIncreasingSubseq_tabu(int[] arr, int[] dp) {
            for (int i = 0; i < arr.length; i++) {
                int max = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] < arr[i]) {
                        max = Math.max(max, dp[j] + 1);
                    }
                }
                dp[i] = max;
            }
        }

        public static void longestDecreasingSubseq_tabu(int[] arr, int[] dp) {
            for (int i = arr.length - 1; i >= 0; i--) {
                int max = 1;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[i]) {
                        max = Math.max(max, dp[j] + 1);
                    }
                }
                dp[i] = max;
            }
        }

        public int LongestBitonicSequence(int[] nums) {
            int[] increasing = new int[nums.length];
            int[] decreasing = new int[nums.length];
            int max = 0;
            longestIncreasingSubseq_tabu(nums, increasing);
            longestDecreasingSubseq_tabu(nums, decreasing);
            for (int i = 0; i < nums.length; i++) {
                max = Math.max(max, increasing[i] + decreasing[i] - 1);
            }
            // for(int i=0;i<nums.length;i++)
            // {
            // System.out.println(increasing[i]+" "+decreasing[i]);
            // }
            return max;
        }
    }

    // Maximum sum increasing subsequence
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    class Solution2 {
        public static int longestIncreasingSubseq_tabu(int[] arr, int[] dp) {
            for (int i = 0; i < arr.length; i++) {
                int max = arr[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] < arr[i]) {
                        max = Math.max(max, dp[j] + arr[i]);
                    }
                }
                dp[i] = max;
            }

            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        public int maxSumIS(int arr[], int n) {
            int[] dp = new int[arr.length];
            return longestIncreasingSubseq_tabu(arr, dp);
        }
    }

    // Maximum Sum Bitonic Subsequence
    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1
    class Compute {
        public static void longestIncreasingSubseq_tabu(int[] arr, int[] dp) {
            for (int i = 0; i < arr.length; i++) {
                int max = arr[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] < arr[i]) {
                        max = Math.max(max, dp[j] + arr[i]);
                    }
                }
                dp[i] = max;
            }
        }

        public static void longestDecreasingSubseq_tabu(int[] arr, int[] dp) {
            for (int i = arr.length - 1; i >= 0; i--) {
                int max = arr[i];
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[i]) {
                        max = Math.max(max, dp[j] + arr[i]);
                    }
                }
                dp[i] = max;
            }
        }

        public static int maxSumBS(int arr[], int n) {
            int[] increasing = new int[arr.length];
            int[] decreasing = new int[arr.length];
            int max = 0;
            longestIncreasingSubseq_tabu(arr, increasing);
            longestDecreasingSubseq_tabu(arr, decreasing);
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max, increasing[i] + decreasing[i] - arr[i]);
            }
            return max;
        }
    }

    // Minimum no. of deletions required to make array sorted
    // i.e, varaince of LIS with a subtle case(<= condition,for cases like
    // this->{2,3,3,6,1})
    public static int longestIncreasingSubseq_tabu(int[] arr, int[] dp) {
        for (int i = 0; i < arr.length; i++) {
            int max = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int minDeletions(int arr[], int n) {
        int[] dp = new int[n];
        return n - longestIncreasingSubseq_tabu(arr, dp);
    }

    // 673. Number of Longest Increasing Subsequence
    class Solution3 {
        public static int longestIncreasingSubseq_tabu(int[] arr, int[] dp, int[] count) {
            int maxLen = 0;
            int maxCount = 0;
            for (int i = 0; i < arr.length; i++) {
                int max = 1;
                int check = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] < arr[i]) {
                        max = Math.max(max, dp[j] + 1);
                    }
                }
                dp[i] = max;
                // checking for count now
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] < arr[i]) {
                        if (dp[j] + 1 == dp[i]) {
                            check += count[j];
                        }
                    }
                }
                check = (check == 0 ? 1 : check);
                dp[i] = max;
                count[i] = check;
                // now finding maxlen and macCount
                if (maxLen < max) {
                    maxLen = max;
                    maxCount = check;
                } else if (maxLen == max) {
                    maxCount += check;
                }
            }
            return maxCount;
        }

        public int findNumberOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int[] count = new int[nums.length];
            return longestIncreasingSubseq_tabu(nums, dp, count);
        }
    }

    // Good Question
    // Building Bridges
    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    private static int maximumBridges(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int max = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private static void BridgesCall() {
        // int[][] arr = { { 6, 2 }, { 4, 3 }, { 2, 6 }, { 1, 5 } };
        int[][] arr = { { 8, 1 }, { 1, 2 }, { 4, 3 }, { 3, 4 }, { 5, 5 }, { 2, 6 }, { 6, 7 }, { 7, 3 } };
        System.out.println(maximumBridges(arr));
    }

    // 354. Russian Doll Envelopes
    // https://leetcode.com/problems/russian-doll-envelopes/
    //Note:this is resulting in TLE ,we need to use binarysearch for AC
    class Solution4 {
        private static int maximumBridges(int[][] arr) {
            Arrays.sort(arr, (a, b) -> {
                return a[1] - b[1];
            });
            int[] dp = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int max = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1]) {
                        max = Math.max(max, dp[j] + 1);
                    }
                }
                dp[i] = max;
            }

            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        public int maxEnvelopes(int[][] envelopes) {
            return maximumBridges(envelopes);
        }
    }

    public static void main(String[] args) {
        // LISCall();
        // LDSCall();
        BridgesCall();

    }
}
