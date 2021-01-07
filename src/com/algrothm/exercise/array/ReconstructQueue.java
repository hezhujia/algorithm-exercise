package com.algrothm.exercise.array;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.Comparator;
import java.util.List;

public class ReconstructQueue {
    public static int[][] reconstructQueue(int[][] people) {
        for (int i = 0; i < people.length; i++) {
            int index = i;
            int[] match = people[i];
            for (int j = i+1; j < people.length; j++) {
                // 查找符合条件的放在前面
//                if (people[j][0] < match[0] || ())
            }
        }

        return people;
    }

    static class TempTestCase implements TestCase<int[][]> {

        int[][] testPeople;
        int[][] exceptedResult;

        TempTestCase(String testPeople, String exceptedResult) {
            this.testPeople = ParseArgs.changeStringToTwoDimensionIntArray(testPeople);
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
                .addCase(new TempTestCase("[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]", "[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]"))
                .build();

        ExecTestCases.exec(testCases,
                tempTestCase -> reconstructQueue(tempTestCase.testPeople),
                Comparator.comparing(ParseArgs::changeTwoDimensionIntArrayToString));
    }
}
