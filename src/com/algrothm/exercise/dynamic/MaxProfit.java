package com.algrothm.exercise.dynamic;

public class MaxProfit {
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int[] leftMax = new int[prices.length];
        int[] rightMax = new int[prices.length];

        int min = prices[0];
        leftMax[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            leftMax[i] = Math.max(prices[i]-min, leftMax[i-1]);
        }

        int max = prices[prices.length-1];
        rightMax[prices.length-1] = 0;
        for (int i = prices.length-2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            rightMax[i] = Math.max(max-prices[i], rightMax[i+1]);
        }

        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            result = Math.max(leftMax[i]+rightMax[i], result);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,3,5,0,0,3,1,4}));
    }
}
