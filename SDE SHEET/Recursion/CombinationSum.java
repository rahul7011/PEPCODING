import java.util.*;
public class CombinationSum {
    class Solution {
        private int combinationSum(int[] coins, int tar, int idx, List<List<Integer>> ans, List<Integer> smallAns) {
            if (tar == 0) {
                ans.add(new ArrayList<>(smallAns));
                return 1;
            }
            int count = 0;
            for (int i = idx; i < coins.length; i++) {
                if (tar - coins[i] >= 0) {
                    smallAns.add(coins[i]);
                    count += combinationSum(coins, tar - coins[i], i, ans, smallAns);
                    smallAns.remove(smallAns.size() - 1);
                }
            }
            return count;
        }

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            // combination ifinite
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> smallAns = new ArrayList<>();
            combinationSum(candidates, target, 0, ans, smallAns);
            return ans;
        }
    }
}
