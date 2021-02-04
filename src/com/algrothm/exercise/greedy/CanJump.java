package com.algrothm.exercise.greedy;

public class CanJump {
    public static boolean canJump(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length-1; i++) {
            maxLength = Math.max(maxLength, i + nums[i]);
            if (maxLength <= i) {
                return false;
            }
        }

        return true;
    }

    // 跳到最后一步的最小步数
    public static int jump(int[] nums) {
        int minJump = 0;
        int preMaxLength = 0;
        int curMaxLength = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            // 当前可以跳
            if (curMaxLength < i + nums[i]) {
                curMaxLength = i + nums[i];
            }
            if (i >= preMaxLength) {
                preMaxLength = curMaxLength;
                minJump++;
            }
        }

        return minJump;
    }

    public static void main(String[] args) {
//        System.out.println(canJump(new int[]{3,2,1,0,4}));
        System.out.println(jump(new int[]{2,0,1,3,4}));
        System.out.println(jump(new int[]{2, 3, 1}));
        System.out.println(jump(new int[]{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3}));
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
    }
}
