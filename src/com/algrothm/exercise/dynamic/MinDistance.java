package com.algrothm.exercise.dynamic;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.List;

public class MinDistance {
    public static int minDistance(String word1, String word2) {
        // 对于子字符串word1(i, end)和word2(j, end)转换步骤 distance[i][j]，若 i != j
        // 替换 distance[i][j] = distance[i+1][j+1] + 1
        // 删除 distance[i][j] = distance[i+1][j] + 1
        // 添加 distance[i][j] = distance[i][j+1] + 1

        // 对于distance[length1-1][length2-1], distance[length1-1][length2-2], distance[length1-2][length2-1]的求值
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }

        int[][] distance = new int[word1.length()][word2.length()];
        int length1 = word1.length();
        int length2 = word2.length();

        for (int i = length1-1; i >= 0; i--) {
            for (int j = length2-1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    if (i == length1-1 && j == length2-1) {
                        distance[i][j] = 0;
                    } else if (i == length1-1) {
                        distance[i][j] = length2-1-j;
                    } else if (j == length2-1) {
                        distance[i][j] = length1-1-i;
                    } else {
                        distance[i][j] = distance[i+1][j+1];
                    }
                } else {
                    if (i == length1-1 && j == length2-1) {
                        distance[i][j] = 1;
                    } else if (i == length1-1) {
                        distance[i][j] = distance[i][j+1] + 1;
                    } else if (j == length2-1) {
                        distance[i][j] = distance[i+1][j] + 1;
                    } else {
                        distance[i][j] = Math.min(Math.min(distance[i+1][j+1], distance[i][j+1]), distance[i+1][j]) + 1;
                    }
                }
            }
        }

        return distance[0][0];
    }

    // 滚动数组
    public static int minDistanceWithScrollingArray(String word1, String word2) {
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }

        if (word1.length() > word2.length()) {
            return minDistanceWithScrollingArray(word2, word1);
        }

        int[] distance = new int[word2.length()+1];
        int lastDistance;

        for (int j = 0; j <= word2.length(); j++) {
            distance[j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            lastDistance = distance[0];
            distance[0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                int temp = distance[j];
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    distance[j] = lastDistance;
                } else {
                    distance[j] = Math.min(lastDistance, Math.min(distance[j], distance[j-1])) + 1;
                }
                lastDistance = temp;
            }
        }
        return distance[word2.length()];
    }

    static class TempTestCase implements TestCase<Integer> {

        String testWord1;
        String testWord2;
        int exceptedResult;

        TempTestCase(String testWord1, String testWord2, int exceptedResult) {
            this.testWord1 = testWord1;
            this.testWord2 = testWord2;
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Integer getExceptedResult() {
            return exceptedResult;
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("e", "e", 0))
                .addCase(new TempTestCase("horse", "ros", 3))
                .addCase(new TempTestCase("spartan", "part", 3))
                .addCase(new TempTestCase("intention", "execution", 5))
                .build();

        ExecTestCases.exec(testCases, tempTestCase -> minDistanceWithScrollingArray(tempTestCase.testWord1, tempTestCase.testWord2));
    }
}
