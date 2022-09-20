public class buyAndSell {
    // 121. Best Time to Buy and Sell Stock
    class Solution {
        // basically in this question k(k==1) transaction is only allowed
        public int maxProfit(int[] prices) {
            int dp_i_1_0 = 0, dp_i_1_1 = (int) -1e9;
            for (int price : prices) {
                dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + price);
                dp_i_1_1 = Math.max(dp_i_1_1, 0 - price);
            }
            return dp_i_1_0;
        }
    }
}

// 123. Best Time to Buy and Sell Stock III
class Solution {
    public int maxProfit(int[] prices) {
        // basically in this question k(k==2) transaction is only allowed(if there are
        // more than 2 transactions allowed then we would be using arrays to store
        // answer)
        int dp_i_1_0 = 0, dp_i_1_1 = (int) -1e9;
        int dp_i_2_0 = 0, dp_i_2_1 = (int) -1e9;
        for (int price : prices) {
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + price);
            dp_i_1_1 = Math.max(dp_i_1_1, 0 - price);

            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + price);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - price);
        }
        return dp_i_2_0;
    }
}

// 122. Best Time to Buy and Sell Stock II
class Solution1 {
    public int maxProfit(int[] prices) {
        // basically in this question k(k==inf) transaction is allowed(means relevance
        // of k is now lost)
        int dp_i_k_0 = 0, dp_i_k_1 = (int) -1e9;
        for (int price : prices) {
            int oldSellPrice = dp_i_k_0; // max profit generated by k-1 times transaction.
            dp_i_k_0 = Math.max(dp_i_k_0, dp_i_k_1 + price);
            dp_i_k_1 = Math.max(dp_i_k_1, oldSellPrice - price);
        }
        return dp_i_k_0;
    }
}

// 714. Best Time to Buy and Sell Stock with Transaction Fee
class Solution2 {
    public int maxProfit(int[] prices, int fee) {
        int dp_i_k_0 = 0, dp_i_k_1 = (int) -1e9;
        for (int price : prices) {
            int oldSellPrice = dp_i_k_0; // max profit generated by k-1 times transaction.
            dp_i_k_0 = Math.max(dp_i_k_0, dp_i_k_1 + price);
            dp_i_k_1 = Math.max(dp_i_k_1, oldSellPrice - price - fee);
        }
        return dp_i_k_0;
    }
}

// 309. Best Time to Buy and Sell Stock with Cooldown
class Solution3 {
    public int maxProfit(int[] prices) {
        int dp_i_k_0 = 0, dp_i_k_1 = (int) -1e9;
        int k_minus_2SellPrice = 0;
        for (int price : prices) {
            int oldSellPrice = dp_i_k_0; // max profit generated by k-1 times transaction.
            dp_i_k_0 = Math.max(dp_i_k_0, dp_i_k_1 + price);
            dp_i_k_1 = Math.max(dp_i_k_1, k_minus_2SellPrice - price);
            k_minus_2SellPrice = oldSellPrice;
        }
        return dp_i_k_0;
    }
}

// 188. Best Time to Buy and Sell Stock IV
class Solution4 {
    public int maxProfit(int k, int[] prices) {
        // basically in this question k transaction is only allowed
        int[][][] dp = new int[prices.length][k][2];
        if (k == 0 || prices.length == 0) {
            return 0;
        }
        dp[0][0][0] = 0;
        dp[0][0][1] = (int) -1e9;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            for (int j = 0; j < k; j++) {
                dp[i][j][0] = Math.max(i == 0 ? 0 : dp[i - 1][j][0], i == 0 ? (int) -1e9 : dp[i - 1][j][1] + price);
                dp[i][j][1] = Math.max(i == 0 ? (int) -1e9 : dp[i - 1][j][1],
                        (i == 0 || j == 0) ? 0 - price : dp[i - 1][j - 1][0] - price);
            }
        }
        return dp[prices.length - 1][k - 1][0];
    }
}