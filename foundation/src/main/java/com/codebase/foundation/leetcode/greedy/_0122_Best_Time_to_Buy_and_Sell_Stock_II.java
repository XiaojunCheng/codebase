package com.codebase.foundation.leetcode.greedy;

public class _0122_Best_Time_to_Buy_and_Sell_Stock_II {

    /**
     * Runtime: 1 ms, faster than 77.78% of Java online submissions for Best Time to Buy and Sell Stock II.
     * Memory Usage: 38.6 MB, less than 55.07% of Java online submissions for Best Time to Buy and Sell Stock II.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxProfit;
    }

}
