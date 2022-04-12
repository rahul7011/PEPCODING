package Recursion;
import java.util.*;

public class l3_Problems {

    // leetcode 17
    /*
     * Using coin change combination single + coin change permutation single but it
     * can also be implemented
     * using coin change combination single using subsequence + coin change
     * permutation single subsequence but here first method
     * is preferred
     * as it is easy to implement in this case
     */
    public int letterCombinations(String digits, String[] codes, int idx, List<String> ans, String psf) {
        if (idx == digits.length()) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        int digit = digits.charAt(idx) - '0';
        String code = codes[digit];
        for (int i = 0; i < code.length(); i++) {
            char val = code.charAt(i);
            count += letterCombinations(digits, codes, idx + 1, ans, psf + val);
        }
        return count;
    }

    public List<String> letterCombinations(String digits) {
        String[] codes = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        if (digits.length() == 0)
            return new ArrayList<>();

        List<String> ans = new ArrayList<>();
        letterCombinations(digits, codes, 0, ans, "");
        return ans;
    }

    // leetcode 518
    /*
     * Approach 1
     * solved using coin change combination infinite subsequence
     */
    private int change(int[] coins, int tar, int idx) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += change(coins, tar - coins[idx], idx);
        }
        count += change(coins, tar, idx + 1);
        return count;
    }

    public int change(int amount, int[] coins) {
        return change(coins, amount, 0);
    }

    /*
     * Approach 2
     * solved using coin change combination infinite
     */
    private int change2(int[] coins, int tar, int idx) {
        if (tar == 0) {
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += change2(coins, tar - coins[i], i);
            }
        }
        return count;
    }

    public int change2(int amount, int[] coins) {
        return change2(coins, amount, 0);
    }

    // leetcode 322
    /*
     * solved using coin change combination infinite subsequence but
     * it can also be solved using coin change combination infinite
     */
    private int coinChange(int[] coins, int tar, int idx, int count) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0)
                return count;
            return (int) 1e9;
        }
        int ans = (int) 1e9;
        if (tar - coins[idx] >= 0) {
            ans = coinChange(coins, tar - coins[idx], idx, count + 1);
        }
        ans = Math.min(ans, coinChange(coins, tar, idx + 1, count));
        return ans;
    }

    public int coinChange(int[] coins, int amount) {
        int ans = coinChange(coins, amount, 0, 0);
        return ans != (int) 1e9 ? ans : -1;
    }

    // leetcode 46
    /*
     * solved using coin change permutation single
     * but can also be solved using coin change permutation single subsequence
     */

    private int permute(int[] nums, int count, List<List<Integer>> ans, List<Integer> smallAns) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(smallAns));
            return 1;
        }
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > -11) {
                int val = nums[i];
                nums[i] = -11; // Marked
                smallAns.add(val); // Added
                total += permute(nums, count + 1, ans, smallAns);
                nums[i] = val; // UnMarked
                smallAns.remove(smallAns.size() - 1); // removed
            }
        }
        return total;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        permute(nums, 0, ans, smallAns);
        return ans;
    }

    // leetcode 39
    /*
     * solved using coin change combination infinite subsequence
     * but can also be solved using coin change combination infinite
     */
    private static int combinationSum(int[] candidates, int tar, int idx, List<List<Integer>> ans,
            List<Integer> smallAns) {
        if (tar == 0 || idx >= candidates.length) {
            if (tar == 0) {
                ans.add(new ArrayList<>(smallAns));
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - candidates[idx] >= 0) {
            smallAns.add(candidates[idx]);
            count += combinationSum(candidates, tar - candidates[idx], idx, ans, smallAns);
            smallAns.remove(smallAns.size() - 1);
        }
        count += combinationSum(candidates, tar, idx + 1, ans, smallAns);
        return count;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combinationSum(candidates, target, 0, ans, smallAns);
        return ans;
    }

    // leetcode 40
    /*
     * solved using coin change combination single
     * but can also be solved using coin change combination single subsequence
     */

    private static int combinationSum2(int[] candidates, int tar, int idx, List<List<Integer>> ans,
            List<Integer> smallAns) {
        if (tar == 0) {
            ans.add(new ArrayList<>(smallAns));
            return 1;
        }
        int count = 0, prev = -1;
        for (int i = idx; i < candidates.length; i++) {
            if (prev != candidates[i] && tar - candidates[i] >= 0) {
                smallAns.add(candidates[i]);
                count += combinationSum2(candidates, tar - candidates[i], i + 1, ans, smallAns);
                smallAns.remove(smallAns.size() - 1);
                prev = candidates[i];
            }
        }
        return count;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combinationSum2(candidates, target, 0, ans, smallAns);
        return ans;
    }

    // leetcode 47
    /*
     * solved using coin change permutation single
     * but can also be solved using coin change permutation single subsequence
     */
    public static int permuteUnique(int[] nums, int idx, List<List<Integer>> ans, List<Integer> smallAns, int count) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(smallAns));
            return 1;
        }
        int total = 0, prev = -12; // To avoid duplication on same level
        for (int i = 0; i < nums.length; i++) {
            if (prev != nums[i] && nums[i] > -11) {
                int val = nums[i];
                nums[i] = -11;
                smallAns.add(val);
                total += permuteUnique(nums, 0, ans, smallAns, count + 1);
                smallAns.remove(smallAns.size() - 1);
                nums[i] = val;
                prev = val;
            }
        }
        return total;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        permuteUnique(nums, 0, ans, smallAns, 0);
        return ans;
    }
}
