package com.codebase.foundation.leetcode.unclassified;

/**
 * Runtime: 1 ms, faster than 82.58% of Java online submissions for Best Time to Buy and Sell Stock.
 * Memory Usage: 35.6 MB, less than 96.33% of Java online submissions for Best Time to Buy and Sell Stock.
 */
public class _0121_BestTime_to_Buy_and_SellStock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[0]) {
                prices[0] = prices[i];
                continue;
            }

            maxProfit = Math.max(prices[i] - prices[0], maxProfit);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        _0121_BestTime_to_Buy_and_SellStock main = new _0121_BestTime_to_Buy_and_SellStock();
        System.out.println(main.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(main.maxProfit(new int[]{1, 1, 1, 1, 1}));
        System.out.println(main.maxProfit(new int[]{1, 2, 3, 2, 5, 6}));
    }

}
