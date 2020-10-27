package com.algrothm.exercise.unionfindset;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.Arrays;
import java.util.List;

public class MatrixRankTransform {

    public static int[][] matrixRankTransform(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] parent = new int[row][col];
        int[][] rank = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                parent[i][j] = i * row + j;
                rank[i][j] = 1;
            }
        }

        return rank;
    }

    private static int findParent(int i, int j, int[][] parent) {
        while (parent[i][j] != i * parent.length + j) {
            int curParent = parent[i][j];
            i = curParent / parent.length;
            j = curParent % parent.length;
        }
        return parent[i][j];
    }

    private static void merge(int i1, int j1, int i2, int j2, int[][] parent, int[][] rank, int[][] matrix) {
        int root1 = findParent(i1, j1, parent);
        int root2 = findParent(i2, j2, parent);
        if (root1 != root2) {
            i1 = root1 / parent.length;
            j1 = root1 % parent.length;
            i2 = root2 / parent.length;
            j2 = root2 % parent.length;

            if (matrix[i1][j1] < matrix[i2][j2]) {
                rank[i1][j1]++;
                parent[i1][j1] = root2;
            } else if (matrix[i1][j1] > matrix[i2][j2]) {
                parent[i2][j2] = root1;
            }
        }
    }

    static class TempTestCase implements TestCase<int[][]> {

        int[][] testMatrix;
        int[][] exceptedResult;

        TempTestCase(String testMatrix, String exceptedResult) {
            this.testMatrix = ParseArgs.changeStringToTwoDimensionIntArray(testMatrix);
            this.exceptedResult = ParseArgs.changeStringToTwoDimensionIntArray(exceptedResult);
        }

        @Override
        public int[][] getExceptedResult() {
            return exceptedResult;
        }

        @Override
        public String resultToString(int[][] ints) {
            return ParseArgs.changeTwoDimensionIntArrayToString(ints);
        }

        @Override
        public String toString() {
            return "TempTestCase{" +
                    "testMatrix=" + ParseArgs.changeTwoDimensionIntArrayToString(testMatrix) +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("[[1,2],[3,4]]", "[[1,2],[2,3]]"))
                .addCase(new TempTestCase("[[7,7],[7,7]]", "[[1,1],[1,1]]"))
                .addCase(new TempTestCase("[[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]", "[[4,2,3],[1,3,4],[5,1,6],[1,3,4]]"))
                .addCase(new TempTestCase("[[7,3,6],[1,4,5],[9,8,2]]", "[[5,1,4],[1,2,3],[6,3,1]]"))
                .build();
    }
}
