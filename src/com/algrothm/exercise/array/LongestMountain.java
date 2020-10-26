package com.algrothm.exercise.array;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.List;

public class LongestMountain {

    public static int longestMountain(int[] A) {
        int result = 0;
        boolean isUp = true;
        int startMountain = 0;

        for (int i = 1; i < A.length; i++) {
            if (isUp) {
                if (A[i] == A[i-1]) {
                    startMountain = i;
                } else if (A[i] > A[i-1]) {
                    continue;
                } else {
                    if (startMountain == i-1) {
                        startMountain = i;
                    } else {
                        if (i == A.length - 1) {
                            result = Math.max(result, i - startMountain + 1);
                        } else {
                            isUp = !isUp;
                        }
                    }
                }
            } else {
                if (A[i] == A[i-1]) {
                    result = Math.max(result, i - startMountain);
                    isUp = !isUp;
                    startMountain = i;
                } else if (A[i] > A[i-1]) {
                    result = Math.max(result, i - startMountain);
                    isUp = !isUp;
                    startMountain = i-1;
                } else {
                    if (i == A.length - 1) {
                        result = Math.max(result, i - startMountain + 1);
                    }
                }
            }
        }

        return result;
    }

    static class TempTestCase implements TestCase<Integer> {

        int[] testArray;
        int exceptedResult;

        TempTestCase(int[] testArray, int exceptedResult) {
            this.testArray = testArray;
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Integer getExceptedResult() {
            return exceptedResult;
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase(new int[]{2,1,4,7,3,2,5}, 5))
                .addCase(new TempTestCase(new int[]{2, 2, 2}, 0))
                .addCase(new TempTestCase(new int[]{1, 2, 3, 2, 1}, 5))
                .addCase(new TempTestCase(new int[]{1, 2, 1}, 3))
                .addCase(new TempTestCase(new int[]{2, 2, 1, 2}, 0))
                .addCase(new TempTestCase(new int[]{1, 2, 1, 1}, 3))
                .build();

        ExecTestCases.exec(testCases, (tempTestCase -> longestMountain(tempTestCase.testArray)));
    }
}
