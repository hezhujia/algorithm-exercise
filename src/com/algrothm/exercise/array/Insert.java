package com.algrothm.exercise.array;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Insert {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        int i = 0;
        for (; i < intervals.length; i++) {
            // 当前小于newInterval，直接放入result
            if (intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
                continue;
            }
            if (intervals[i][0] > newInterval[1]) {
                result.add(newInterval);
                newInterval = new int[0];
                break;
            }
            if (intervals[i][0] <= newInterval[0] && intervals[i][1] >= newInterval[1]) {
                result.add(intervals[i]);
                newInterval = new int[0];
                i++;
                break;
            }
            if (intervals[i][0] >= newInterval[0] && intervals[i][1] <= newInterval[1]) {
                continue;
            }
            if (intervals[i][0] >= newInterval[0] && intervals[i][1] >= newInterval[1]) {
                newInterval[1] = intervals[i][1];
                continue;
            }
            if (intervals[i][0] <= newInterval[0] && intervals[i][1] <= newInterval[1]) {
                newInterval[0] = intervals[i][0];
                continue;
            }
        }

        if (newInterval.length != 0) {
            result.add(newInterval);
        }
        if (i != intervals.length) {
            for (; i < intervals.length; i++) {
                result.add(intervals[i]);
            }
        }

        return result.toArray(new int[0][]);
    }

    static class TempTestCase implements TestCase<int[][]> {

        int[][] testIntervals;
        int[] testNewInterval;
        int[][] exceptedResult;

        TempTestCase(String testIntervals, int[] testNewInterval, String exceptedResult) {
            this.testIntervals = ParseArgs.changeStringToTwoDimensionIntArray(testIntervals);
            this.testNewInterval = testNewInterval;
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
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("[[1,3],[6,9]]", new int[]{2,5}, "[[1,5],[6,9]]"))
                .addCase(new TempTestCase("[[1,2],[3,5],[6,7],[8,10],[12,16]]", new int[]{4,8}, "[[1,2],[3,10],[12,16]]"))
                .addCase(new TempTestCase("[[]]", new int[]{2,5}, "[[2,5]]"))
                .addCase(new TempTestCase("[[1,5]]", new int[]{2,7}, "[[1,7]]"))
                .addCase(new TempTestCase("[[1,5]]", new int[]{2,3}, "[[1,5]]"))
                .build();

        ExecTestCases.exec(testCases, tempTestCase -> insert(tempTestCase.testIntervals, tempTestCase.testNewInterval),
                Comparator.comparing(ParseArgs::changeTwoDimensionIntArrayToString));
    }
}
