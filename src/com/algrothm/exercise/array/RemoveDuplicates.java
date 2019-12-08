package com.algrothm.exercise.array;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int newArrayLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[newArrayLength] = nums[i];
                newArrayLength++;
            }
        }
        return newArrayLength;
    }
}
