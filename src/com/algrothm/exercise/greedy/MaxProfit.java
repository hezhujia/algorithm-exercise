package com.algrothm.exercise.greedy;

public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;
        int min = prices[0];
        int max = prices[0];

        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i]);
            maxProfit = Math.max(maxProfit, max - min);
        }

        return maxProfit;
    }
}
