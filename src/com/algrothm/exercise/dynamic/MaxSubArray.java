package com.algrothm.exercise.dynamic;

public class MaxSubArray {

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] result = new int[nums.length];
        result[0] = nums[0];
        int maxSub = result[0];

        for (int i = 1; i < nums.length; i++) {
            result[i] = Math.max(result[i-1]+nums[i], nums[i]);
            if (result[i] > maxSub) {
                maxSub = result[i];
            }
        }

        return maxSub;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,1,-5,4}));
    }
}
