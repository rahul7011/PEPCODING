public class l004 {
    // KadanesAlgo
    // [-1,-7,-8,-9] -> max sum Subarray if 0(no subarray exist);
    public static int kadanesAlgo(int[] nums) {
        int cSum = 0, gSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            cSum += ele;
            if (cSum > gSum) {
                gSum = cSum;
            }
            if (cSum <= 0) {
                cSum = 0;
            }
        }
        return gSum;
    }

    // for cases like this:
    // [-1,-7,-8,-9] -> max sum Subarray if -1 (0,0);
    public static int kadanesAlgoGeneric(int[] nums) {
        int cSum = 0, gSum = (int) -1e9;
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            cSum += ele;
            if (cSum > gSum) {
                gSum = cSum;
            }
            if (cSum <= 0) {
                cSum = 0;
            }
        }
        return gSum;
    }

    // 53. Maximum Subarray
    // kadane's algorithm
    class Solution {
        public int maxSubArray(int[] nums) {
            int cSum = 0, gSum = (int) -1e9;
            for (int i = 0; i < nums.length; i++) {
                int ele = nums[i];
                cSum += ele;
                if (cSum > gSum) {
                    gSum = cSum;
                }
                if (cSum <= 0) {
                    cSum = 0;
                }
            }
            return gSum;
        }
    }

    // 1191. K-Concatenation Maximum Sum
    class Solution1 {
        // This is completely based on formula that we derived after doing multiple dry
        // runs
        // formula:(s1+s2)+(k-2)*x
        // where s1=prefixSum s2=suffixSum and x is total sum
        int mod = (int) 1e9 + 7;

        public int kadanesAlgo(int[] arr, int k) {
            int n = arr.length;
            long gsum = 0, csum = 0;

            for (int i = 0; i < k * n; i++) {
                int ele = arr[i % n];
                csum += ele;

                if (csum > gsum)
                    gsum = csum;
                if (csum <= 0)
                    csum = 0;
            }

            return (int) gsum % mod;
        }

        public int kConcatenationMaxSum(int[] arr, int k) {
            int kadansSum = kadanesAlgo(arr, 1);

            if (k == 1)
                return kadansSum;

            long prefixSum = 0, suffixSum = 0, maxPrefixSum = 0, maxSuffixSum = 0, arraySum = 0;
            int n = arr.length;
            for (int i = 0, j = n - 1; i < n; i++, j--) {
                prefixSum += arr[i];
                suffixSum += arr[j];
                arraySum += arr[i];

                maxPrefixSum = Math.max(maxPrefixSum, prefixSum);
                maxSuffixSum = Math.max(maxSuffixSum, suffixSum);
            }

            // if (arraySum > 0)
            // return (int) ((maxPrefixSum + maxSuffixSum + (k - 2) * arraySum) % mod);
            // else
            // return (int) (Math.max(maxPrefixSum + maxSuffixSum, kadansSum) % mod);

            arraySum = arraySum < 0 ? 0 : arraySum % mod;
            return (int) Math.max(kadansSum, maxPrefixSum + maxSuffixSum + ((k - 2) * arraySum) % mod) % mod;
        }
    }
}
