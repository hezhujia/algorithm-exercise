package com.algrothm.exercise.dynamic;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.List;

import static com.algrothm.exercise.utils.ParseArgs.changeStringToTwoDimensionIntArray;
import static com.algrothm.exercise.utils.ParseArgs.changeTwoDimensionIntArrayToString;

public class VideoStitching {

    public static int videoStitching(int[][] clips, int T) {
        int result = 0;
        int maxEnd = 0;

        boolean[] visitClip = new boolean[clips.length];

        for (int i = 0; i < T;) {
            int matchNum = -1;
            for (int j = 0; j < clips.length; j++) {
                if (visitClip[j]) {
                    continue;
                }
                if (clips[j][0] <= i && clips[j][1] > maxEnd) {
                    maxEnd = clips[j][1];
                    matchNum = j;
                    visitClip[j] = true;
                }
            }
            if (matchNum == -1) {
                return -1;
            } else {
                result++;
                i = clips[matchNum][1];
            }
        }
        return result;
    }

    static class TempTestCase implements TestCase<Integer> {
        final int[][] testClips;
        final int testT;
        final int expectedResult;

        TempTestCase(String testClipsStr, int testT, int expectedResult) {
            this.testClips = changeStringToTwoDimensionIntArray(testClipsStr);
            this.testT = testT;
            this.expectedResult = expectedResult;
        }

        @Override
        public Integer getExceptedResult() {
            return expectedResult;
        }

        @Override
        public String toString() {
            return "TempTestCase{" +
                    "testClips=" + changeTwoDimensionIntArrayToString(testClips) +
                    ", testT=" + testT +
                    ", expectedResult=" + expectedResult +
                    '}';
        }
    }

    public static void main(String[] args) {

        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]", 10, 5))
                .build();

        ExecTestCases.exec(testCases,
                (testCase) -> videoStitching(testCase.testClips, testCase.testT));
    }

}

