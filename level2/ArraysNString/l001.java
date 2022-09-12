public class l001 {

    // 189. Rotate Array
    class Solution {
        private static void reverse(int[] nums, int left, int right) {
            while (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            k = n - k;
            reverse(nums, 0, k - 1);
            reverse(nums, k, n - 1);
            reverse(nums, 0, n - 1);
        }
    }

    // 905. Sort Array By Parity
    class Solution1 {
        public int[] sortArrayByParity(int[] nums) {
            int even = 0;
            int odd = 0;
            // same as Sort 01
            while (odd < nums.length) {
                if ((nums[odd] & 1) == 0) {
                    // swap
                    int temp = nums[even];
                    nums[even] = nums[odd];
                    nums[odd] = temp;
                    even++;
                    odd++;
                } else {
                    odd++;
                }
            }
            return nums;
        }
    }

    // 283. Move Zeroes
    class Solution2 {
        public void moveZeroes(int[] nums) {
            int zero = 0, nonZero = 0;
            while (nonZero < nums.length) {
                if (nums[nonZero] != 0) {
                    int temp = nums[nonZero];
                    nums[nonZero] = nums[zero];
                    nums[zero] = temp;
                    nonZero++;
                    zero++;
                } else {
                    nonZero++;
                }
            }
        }
    }

    // Sort an array of 0s, 1s and 2s
    // https://practice.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s4231/1
    class Solution3 {
        public static void sort012(int nums[], int n) {
            int zero = 0, one = 0, two = n - 1;
            while (one <= two) {
                if (nums[one] == 0) {
                    int temp = nums[one];
                    nums[one] = nums[zero];
                    nums[zero] = temp;
                    one++;
                    zero++;
                } else if (nums[one] == 2) {
                    // System.out.println(one);
                    int temp = nums[one];
                    nums[one] = nums[two];
                    nums[two] = temp;
                    two--;
                } else {
                    one++;
                }
            }
        }
    }

    // Max sum in the configuration
    // https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
    class GfG {
        int max_sum(int A[], int n) {
            int max = 0;
            int totalSum = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int x = A[i];
                sum += (x * i);
                totalSum += x;
            }
            max = sum;
            for (int i = 1; i < n; i++) {
                sum = sum - totalSum + (n * A[i - 1]);
                max = Math.max(max, sum);
            }
            return max;
        }
    }

    // 11. Container With Most Water
    class Solution4 {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int max = 0;
            while (left < right) {
                int h = Math.min(height[left], height[right]);
                int w = right - left;
                int area = h * w;
                max = Math.max(max, area);
                if (height[left] < height[right]) {
                    left++;
                } else if (height[left] > height[right]) {
                    right--;
                } else {
                    left++;
                    right--;
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {

    }
}