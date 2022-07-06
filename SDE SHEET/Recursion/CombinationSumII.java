import java.util.*;

public class CombinationSumII {
    class Solution {
        private int combinationSum2(int[] coins, int tar, int idx, List<List<Integer>> ans, List<Integer> smallAns) {
            if (tar == 0) {
                ans.add(new ArrayList<>(smallAns));
                return 1;
            }
            int count = 0;
            int prev = -1;
            for (int i = idx; i < coins.length; i++) {
                if (prev != coins[i] && (tar - coins[i]) >= 0) {
                    smallAns.add(coins[i]);
                    count += combinationSum2(coins, tar - coins[i], i + 1, ans, smallAns);
                    smallAns.remove(smallAns.size() - 1);
                }
                prev = coins[i];
            }
            return count;
        }

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            // combination infinite with unique constraint
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> smallAns = new ArrayList<>();
            Arrays.sort(candidates);
            combinationSum2(candidates, target, 0, ans, smallAns);
            return ans;
        }
    }
}
