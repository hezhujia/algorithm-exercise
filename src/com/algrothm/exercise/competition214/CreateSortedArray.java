package com.algrothm.exercise.competition214;

import com.algrothm.exercise.search.BasicSearch;
import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.ArrayList;
import java.util.List;

public class CreateSortedArray {

    public static int createSortedArray(int[] instructions) {
        long result = 0;

        List<Integer> sortedArray = new ArrayList<>();
        sortedArray.add(instructions[0]);

        for (int i = 1; i < instructions.length; i++) {
            int nextNum = instructions[i];
            int minIndex = BasicSearch.binarySearchLess(sortedArray, nextNum, 0, sortedArray.size()-1);
            if (minIndex == -1) {
                sortedArray.add(0, nextNum);
                continue;
            }
            int maxIndex = BasicSearch.binarySearchLarge(sortedArray, nextNum, minIndex, sortedArray.size()-1);
            if (maxIndex == -1) {
                sortedArray.add(minIndex+1, nextNum);
                continue;
            }
            result += Math.min(minIndex+1, sortedArray.size()-maxIndex);
            sortedArray.add(minIndex+1, nextNum);
        }

        return (int) (result%1000000007);
    }

    static class TempTestCase implements TestCase<Integer> {
        int[] testInstructions;
        int exceptedResult;

        TempTestCase(int[] testInstructions, int exceptedResult) {
            this.testInstructions = testInstructions;
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Integer getExceptedResult() {
            return exceptedResult;
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase(new int[]{1,5,6,2}, 1))
                .addCase(new TempTestCase(new int[]{1,2,3,6,5,4}, 3))
                .addCase(new TempTestCase(new int[]{1,3,3,3,2,4,2,1,2}, 4))
                .build();

        ExecTestCases.exec(testCases, testCase -> createSortedArray(testCase.testInstructions));
    }
}
