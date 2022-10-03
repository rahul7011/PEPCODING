import java.util.HashMap;

public class l003 {
    // 974. Subarray Sums Divisible by K
    class Solution {
        public int subarraysDivByK(int[] nums, int k) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            int count = 0;
            hm.put(0, 1);
            int pre = 0; // prefix sum
            for (int x : nums) {
                pre += x;
                // prefix can be negative therefore we are changing each value to positive by
                // adding k and then taking mod
                if (hm.containsKey(((pre % k) + k) % k) == true) {
                    count += hm.get(((pre % k) + k) % k);
                }
                hm.put(((pre % k) + k) % k, hm.getOrDefault(((pre % k) + k) % k, 0) + 1);
            }
            return count;
        }
    }

    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
    // Subarrays with equal 1s and 0s
    class Solution1 {
        // Function to count subarrays with 1s and 0s.
        static int countSubarrWithEqualZeroAndOne(int nums[], int n) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            int count = 0;
            hm.put(0, 1);
            int pre = 0;
            for (int x : nums) {
                pre += (x == 0 ? -1 : 1);
                if (hm.containsKey(pre) == true) {
                    count += hm.get(pre);
                }
                hm.put(pre, hm.getOrDefault(pre, 0) + 1);
            }
            return count;
        }
    }

    // Longest subarray with sum divisible by K
    // https://practice.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by-k1259/1
    class Solution2 {
        int longSubarrWthSumDivByK(int a[], int n, int k) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            int count = 0;
            hm.put(0, -1);
            int pre = 0;
            for (int i = 0; i < n; i++) {
                int x = a[i];
                pre += x;
                if (hm.containsKey(((pre % k) + k) % k) == true) {
                    count = Math.max(count, i - hm.get(((pre % k) + k) % k));
                } else
                    hm.put(((pre % k) + k) % k, i);
            }
            return count;
        }
    }

    // 525. Contiguous Array
    class Solution3 {
        public int findMaxLength(int[] nums) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            int count = 0;
            hm.put(0, -1);
            int pre = 0, n = nums.length;
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                pre += (x == 0 ? -1 : 1);
                if (hm.containsKey(pre) == true) {
                    count = Math.max(count, i - hm.get(pre));
                } else
                    hm.put(pre, i);
            }
            return count;
        }
    }

    // Leetcode daily but really intitutive approach of two pointer
    // 1578. Minimum Time to Make Rope Colorful
    class Solution4 {
        public int minCost(String colors, int[] neededTime) {
            int n = colors.length();
            int ans = 0;
            int left = 0, right = 0;
            while (left < n) {
                char color = colors.charAt(left);
                int max = (int) -1e9;
                int sum = 0;
                while (right < n && color == colors.charAt(right)) {
                    max = Math.max(max, neededTime[right]);
                    sum += neededTime[right];
                    right++;
                }
                ans += (sum - max);
                left = right;
            }
            return ans;
        }
    }

}
