package com.algrothm.exercise.array;

public class TrappingRainWater {

    public int trap(int[] height) {
        int trapedWater = 0;
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            if (i == 0) {
                maxLeft[i] = height[i];
            } else {
                maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
            }
        }

        for (int i = height.length-1; i >= 0 ; i--) {
            if (i == height.length - 1) {
                maxRight[i] = height[i];
            } else {
                maxRight[i] = Math.max(maxRight[i+1], height[i]);
            }
        }

        for (int i = 0; i < height.length; i++) {
            trapedWater += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        return trapedWater;
    }
}
