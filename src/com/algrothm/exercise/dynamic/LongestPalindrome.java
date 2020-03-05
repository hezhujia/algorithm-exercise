package com.algrothm.exercise.dynamic;

public class LongestPalindrome {

    // 动态规划法
    public static String longestPalindromeWithDynamic(String s) {
        // 使用一个bool记录i,j是否是回文串
        boolean[][] isPalidrome = new boolean[s.length()][s.length()];
        // 记录最长回文子串
        String result = "";

        for (int i = s.length()-1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                // 考虑特殊场景：i==j 或 j-i=1，即 j - i < 2时一定为true
                isPalidrome[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalidrome[i+1][j-1]);

                if (isPalidrome[i][j] && result.length() <= j - i + 1) {
                    result = s.substring(i, j+1);
                }
            }
        }
        return result;
    }

    private static String expandResult = "";

    // 中心扩展法
    public static String longestPalindromeWithExpand(String s) {
        for (int i = 0; i < s.length(); i++) {
            expand(s, i, i);
            expand(s, i, i+1);
        }
        return expandResult;
    }

    private static void expand(String s, int index1, int index2) {
        for (; index1 >= 0 && index2 < s.length(); index1--, index2++) {
            // 找到了不一致的点
            if (s.charAt(index1) != s.charAt(index2)) {
                break;
            }
        }
        if (index2 - index1 - 1 > expandResult.length()) {
            expandResult = s.substring(index1+1, index2);
        }
    }

    public static String longestPalindrome(String s) {
        String[][] subString = new String[s.length()+1][s.length()+1];

        return longestPalindromeWithRecall(s, 0, s.length(), subString);
    }

    public static String longestPalindromeWithRecall(String s, int start, int end, String[][] subString) {
        // 如果只有一个元素，直接返回
        if (end - start <= 1) {
            subString[start][end] = s.substring(start, end);
            return subString[start][end];
        }
        if (subString[start][end] != null) {
            return subString[start][end];
        }
        // 如果首尾元素相等
        if (s.charAt(start) == s.charAt(end-1)) {
            String sub = longestPalindromeWithRecall(s, start+1, end-1, subString);
            if (sub.length() == end-start-2) {
                subString[start][end] = s.substring(start, end);
                return subString[start][end];
            }
        }
        // 如果首尾元素不等，判断那个子字符串的最长回文子串最长
        String sub1 = longestPalindromeWithRecall(s, start, end-1, subString);
        String sub2 = longestPalindromeWithRecall(s, start+1, end, subString);

        String sub = sub1.length() >= sub2.length() ? sub1 : sub2;
        subString[start][end] = sub;
        return sub;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeWithExpand("babaddtattarrattatddetartrateedredividerb"));
    }
}
