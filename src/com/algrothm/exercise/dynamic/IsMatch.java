package com.algrothm.exercise.dynamic;

public class IsMatch {
    public static boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    public static boolean isMatchByDynamic(String s, String p) {
        boolean[][] isMatched = new boolean[s.length()+1][p.length()+1];

        // 对于0长度的后缀s和0长度的后缀p，总是匹配的
        isMatched[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {

            for (int j = p.length()-1; j >= 0; j--) {

                if (i != s.length() && isMatchTwoChar(s, p, i, j)) {
                    isMatched[i][j] = isMatched[i][j] || isMatched[i+1][j+1];
                }
                if (j <= p.length()-2 && p.charAt(j+1) == '*') {
                    isMatched[i][j] = isMatched[i][j] || isMatched[i][j+2];
                }
                if (i != s.length() && j <= p.length()-2 && isMatchTwoChar(s, p, i, j) && p.charAt(j+1) == '*') {
                    isMatched[i][j] = isMatched[i][j] || isMatched[i+1][j];
                }
            }
        }

        return isMatched[0][0];
    }

    private static boolean isMatchTwoChar(String s, String p, int indexS, int indexP) {
        return s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '.';
    }

    private static boolean isMatch(String s, String p, int startS, int startP) {
        // 如果s和p均到最后一个字符
        if (startS == s.length() && startP == p.length()) {
            return true;
        }

        // 先对下一个字符做判断，如果下一个字符为*
        // 匹配0个当前字符：即跳过a*
        // 匹配多个当前字符：则匹配当前字符和*
        if (startP < p.length()-1 && p.charAt(startP+1) == '*') {
            boolean isMatch = isMatch(s, p, startS, startP+2);
            if (isMatch) {
                return true;
            }
            if (startS == s.length()) {
                return false;
            }
            if (s.charAt(startS) == p.charAt(startP) || p.charAt(startP) == '.') {
                return isMatch(s, p, startS+1, startP);
            }
        } else if (s.charAt(startS) == p.charAt(startP)) {
            // 如果当前字符相等，均移动一步
            return isMatch(s, p, startS+1, startP+1);
        } else if (p.charAt(startP) == '.'){
            // 可以匹配一个字符
            return isMatch(s, p, startS+1, startP+1);
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "";
        String p = "c*c*";

        System.out.println(isMatchByDynamic(s, p));
    }
}
