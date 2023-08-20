public class lowerbound {
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

    public static void main(String[] args) {
        System.out.println("Array Length is:" + 10);
        System.out.println(lower_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 18));
        System.out.println(lower_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 21));
        System.out.println(lower_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 2));
        System.out.println(lower_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 50));
    }
}
/*
 *    the next smallest number just greater than or equal to that number
 *
 * Input : 4 6 10 12 18 18 20 20 30 45
 * Output : lower_bound for element 18 at index 4
 * 
 * Input : 4 6 10 12 16 20 28
 * Output : lower_bound for element 18 at index 5
 * 
 * Input : 24 26 40 56
 * Output : lower_bound for element 18 at index 0
 * 
 * Input : 4 6 10 12 16 17
 * Output : lower_bound for element 18 at index 6
 */
