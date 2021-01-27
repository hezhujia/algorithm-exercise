package com.algrothm.exercise.dynamic;

public class NumDecodings {
    public static int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int[] nums = new int[s.length()+1];
        nums[0] = 0;
        nums[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= s.length(); i++) {
            int lastTwoChar = Integer.parseInt(s.substring(i-2, i));
            if (lastTwoChar == 0) {
                return 0;
            } else if (s.charAt(i-2) == '0') {
                nums[i] = nums[i-1];
            } else if (s.charAt(i-1) == '0' && lastTwoChar <= 26) {
                nums[i] = i == 2 ? 1 : nums[i-2];
            } else if (s.charAt(i-1) == '0' && lastTwoChar > 26) {
                return 0;
            } else if (lastTwoChar <= 26) {
                nums[i] = i == 2 ? nums[i-1] + 1 : nums[i-1] + nums[i-2];
            } else {
                nums[i] = nums[i-1];
            }
        }
        return nums[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("10"));
    }
}
