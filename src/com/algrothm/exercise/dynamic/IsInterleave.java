package com.algrothm.exercise.dynamic;

import java.util.HashMap;
import java.util.Map;

public class IsInterleave {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length()+s2.length()) {
            return false;
        }

        boolean[][] isInterleave = new boolean[s1.length()+1][s2.length()+1];
        isInterleave[0][0] = true;

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i > 0) {
                    isInterleave[i][j] = isInterleave[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1);
                }
                if (j > 0) {
                    isInterleave[i][j] = isInterleave[i][j] || (isInterleave[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                }
            }
        }

        return isInterleave[s1.length()][s2.length()];
    }

    // 滚动数组，优化
    public static boolean isInterleaveTwo(String s1, String s2, String s3) {
        if (s3.length() != s1.length()+s2.length()) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return isInterleaveTwo(s2, s1, s3);
        }

        boolean[] isInterleave = new boolean[s2.length()+1];
        isInterleave[0] = true;

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i > 0) {
                    isInterleave[j] = isInterleave[j] && s1.charAt(i-1) == s3.charAt(i+j-1);
                }
                if (j > 0) {
                    isInterleave[j] = isInterleave[j] || (isInterleave[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                }
            }
        }

        return isInterleave[s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(isInterleaveTwo("aabcc", "dbbca", "aadbbcbcac"));
    }
}
