package com.algrothm.exercise.dynamic;

import java.util.Arrays;

public class MinCut {

    // DP1：最小切割数
    // DP2：是否是回文 x[i][j] x[i+1][j-1] 是回文；或 i == j

    public static int minCut(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        int[] cutNum = new int[s.length()+1];
        for (int i = 0; i < cutNum.length; i++) {
            cutNum[i] = s.length()-1-i;
        }

        // 子串是否是回文
        for (int i = s.length()-1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j-i < 2 || isPalindrome[i+1][j-1])) {
                    isPalindrome[i][j] = true;
                    cutNum[i] = Math.min(cutNum[i], cutNum[j+1]+1);
                }
            }
        }

        return cutNum[0];
    }

    public static void main(String[] args) {
        System.out.println(minCut("givenastringspartitionssuchhateverysubstringofthepartitionisapalindrome"));
    }
}
