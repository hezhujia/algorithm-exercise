package com.algrothm.exercise.search;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumEffortPath {

    // 二分查找+BFS
    public static int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[] fourDirectionRow = new int[]{-1, 1, 0, 0};
        int[] fourDirectionCol = new int[]{0, 0, -1, 1};

        int l = -1;
        int r = 1000000;

        while (l < r) {
            int mid = (l + r) / 2;

            // BFS
            boolean[][] visited = new boolean[row][col];
            Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
            queue.add(new Pair<>(0, 0));
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                Pair<Integer, Integer> visitPosition = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextPosRow = visitPosition.getKey() + fourDirectionRow[i];
                    int nextPosCol = visitPosition.getValue() + fourDirectionCol[i];
                    if (nextPosRow >= 0 && nextPosCol >= 0 && nextPosRow < row && nextPosCol < col && !visited[nextPosRow][nextPosCol]) {
                        if (Math.abs(heights[visitPosition.getKey()][visitPosition.getValue()] - heights[nextPosRow][nextPosCol]) <= mid) {
                            visited[nextPosRow][nextPosCol] = true;
                            queue.add(new Pair<>(nextPosRow, nextPosCol));
                        }
                    }
                }
            }

            if (visited[row-1][col-1]) {
                r = mid;
            } else {
                l = mid+1;
            }
        }

        return r;
    }

    static class TempTestCase implements TestCase<Integer> {
        int[][] testHeights;
        int exceptedResult;

        TempTestCase(String arrayStr, int exceptedResult) {
            this.testHeights = ParseArgs.changeStringToTwoDimensionIntArray(arrayStr);
            this.exceptedResult = exceptedResult;
        }

        @Override
        public String toString() {
            return "TempTestCase{" +
                    "testHeights=" + ParseArgs.changeTwoDimensionIntArrayToString(testHeights) +
                    '}';
        }

        @Override
        public Integer getExceptedResult() {
            return exceptedResult;
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("[[1,2,2],[3,8,2],[5,3,5]]", 2))
                .addCase(new TempTestCase("[[1,2,3],[3,8,4],[5,3,5]]", 1))
                .addCase(new TempTestCase("[[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]", 0))
                .build();

        ExecTestCases.exec(testCases, tempTestCase -> minimumEffortPath(tempTestCase.testHeights));
    }
}
