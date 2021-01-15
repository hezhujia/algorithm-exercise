package com.algrothm.exercise.dynamic;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsScramble {
    static Map<String, Boolean> isScramble = new HashMap<>();

    public static boolean isScrambleWithRecursive(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            isScramble.put(s1+"%"+s2, true);
            return true;
        }
        if (isScramble.containsKey(s1+"%"+s2)) {
            return isScramble.get(s1+"%"+s2);
        }

        int length = s1.length();

        for (int i = 1; i < length; i++) {
            if ((isScrambleWithRecursive(s1.substring(0, i), s2.substring(length-i))
                    && isScrambleWithRecursive(s1.substring(i), s2.substring(0, length-i)))
                    ||
                    (isScrambleWithRecursive(s1.substring(0, i), s2.substring(0, i))
                    && isScrambleWithRecursive(s1.substring(i), s2.substring(i)))) {
                isScramble.put(s1+"%"+s2, true);
                return true;
            }
        }
        isScramble.put(s1+"%"+s2, false);
        return false;
    }

    public static boolean isScrambleWithDP(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        int length = s1.length();

        // 三维数组
        // 第一维：需要判断是否互为扰乱字符的字符串长度
        // 第二维：s1子字符串的开始位置
        // 第三维：s2子字符串的开始位置
        // 结果：f[length][0][0]
        boolean[][][] f = new boolean[length][length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    f[1][i][j] = true;
                }
            }
        }

        for (int n = 2; n <= length; n++) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    for (int k = 0; k < n; k++) {
//                        if (f[])
                    }
                }
            }
        }

        return f[length][0][0];
    }

    static class TempTestCase implements TestCase<Boolean> {
        String testS1;
        String testS2;
        boolean exceptedResult;

        TempTestCase(String testS1, String testS2, boolean exceptedResult) {
            this.testS1 = testS1;
            this.testS2 = testS2;
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Boolean getExceptedResult() {
            return exceptedResult;
        }

        @Override
        public String toString() {
            return "TempTestCase{" +
                    "testS1='" + testS1 + '\'' +
                    ", testS2='" + testS2 + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("abc", "bca", true))
                .addCase(new TempTestCase("abcde", "caebd", false))
                .addCase(new TempTestCase("great", "rgtae", true))
                .addCase(new TempTestCase("great", "rgeat", true))
                .addCase(new TempTestCase("abc", "s", false))
                .addCase(new TempTestCase("", "", true))
                .addCase(new TempTestCase("a", "b", false))
                .build();

        ExecTestCases.exec(testCases, tempTestCase -> isScrambleWithRecursive(tempTestCase.testS1, tempTestCase.testS2));
    }
}
