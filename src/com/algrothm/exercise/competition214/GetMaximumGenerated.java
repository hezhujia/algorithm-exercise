package com.algrothm.exercise.competition214;

public class GetMaximumGenerated {

    public static int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }

        int[] nums = new int[n+1];
        int maxNum = 0;
        nums[0] = 0;
        nums[1] = 1;

        for (int i = 2; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
        }

        return maxNum;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(4));
    }
}
