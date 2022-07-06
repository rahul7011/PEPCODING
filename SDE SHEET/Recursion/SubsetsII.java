import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    class Solution {
        private int subsetsWithDup(int[] nums, int idx, List<List<Integer>> ans, List<Integer> smallAns, int prev) {
            if (idx == nums.length) {
                ans.add(new ArrayList<>(smallAns));
                return 1;
            }
            int count = 0;
            smallAns.add(nums[idx]);
            count += subsetsWithDup(nums, idx + 1, ans, smallAns, nums[idx]);
            smallAns.remove(smallAns.size() - 1);

            if (prev != nums[idx]) {
                count += subsetsWithDup(nums, idx + 1, ans, smallAns, prev);
            }

            return count;
        }

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<Integer> smallAns = new ArrayList<>();
            List<List<Integer>> ans = new ArrayList<>();
            Arrays.sort(nums);
            subsetsWithDup(nums, 0, ans, smallAns, -99);
            return ans;
        }
    }
}
