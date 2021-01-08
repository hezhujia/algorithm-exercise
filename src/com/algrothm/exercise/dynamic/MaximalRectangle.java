package com.algrothm.exercise.dynamic;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.Arrays;
import java.util.List;

public class MaximalRectangle {

    // 暴力破解
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length <= 0) {
            return 0;
        }

        int max = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] lengthOne = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                if (j == 0) {
                    lengthOne[i][j] = 1;
                } else {
                    lengthOne[i][j] = lengthOne[i][j-1] + 1;
                }

                int minWidth = lengthOne[i][j];
                for (int k = i; k >= 0 ; k--) {
                    int height = i - k + 1;
                    minWidth = Math.min(minWidth, lengthOne[k][j]);
                    max = Math.max(minWidth * height, max);
                }
            }
        }

        return max;
    }

    // 动态规划
    public static int maximalRectangleByDP(char[][] matrix) {
        if (matrix.length <= 0) {
            return 0;
        }

        int max = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] height = new int[col];  // 连续最高的1
        int[] L = new int[col];  // 最大的L
        int[] R = new int[col];  // 最小的R
        Arrays.fill(R, col);

        for (int i = 0; i < row; i++) {
            int left = 0; //
            int right = col;
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                    L[j] = Math.max(L[j], left);
                } else {
                    height[j] = 0;
                    left = j+1;
                    L[j] = 0;
                }
            }
            for (int j = col-1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    R[j] = Math.min(R[j], right);
                    max = Math.max(max, height[j] * (R[j]-L[j]));
                } else {
                    right = j;
                    R[j] = col;
                }
            }
        }

        return max;
    }

    static class TempTestClass implements TestCase<Integer> {

        char[][] testMatrix;
        int exceptedResult;

        public TempTestClass(String testMatrix, int exceptedResult) {
            this.testMatrix = ParseArgs.changeStringToTwoDimensionCharArray(testMatrix);
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Integer getExceptedResult() {
            return exceptedResult;
        }

        @Override
        public String toString() {
            return "TempTestClass{" +
                    "testMatrix=" + ParseArgs.changeTwoDimensionCharArrayToString(testMatrix) +
                    ", exceptedResult=" + exceptedResult +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<TempTestClass> testClasses = new ExecTestCases.TestCaseArrayBuilder<TempTestClass>()
//                .addCase(new TempTestClass("[[\"1\",\"0\",\"1\",\"0\",\"0\"],[\"1\",\"0\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"0\",\"0\",\"1\",\"0\"]]", 6))
//                .addCase(new TempTestClass("[]", 0))
//                .addCase(new TempTestClass("[[\"0\"]]", 0))
//                .addCase(new TempTestClass("[[\"1\"]]", 1))
//                .addCase(new TempTestClass("[[\"0\",\"0\"]]", 0))
                .addCase(new TempTestClass("[[\"0\",\"1\",\"1\",\"0\",\"1\"],[\"1\",\"1\",\"0\",\"1\",\"0\"],[\"0\",\"1\",\"1\",\"1\",\"0\"],[\"1\",\"1\",\"1\",\"1\",\"0\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"0\",\"0\",\"0\",\"0\",\"0\"]]\n", 9))
                .build();

        ExecTestCases.exec(testClasses, tempTestClass -> maximalRectangleByDP(tempTestClass.testMatrix));
    }
}
