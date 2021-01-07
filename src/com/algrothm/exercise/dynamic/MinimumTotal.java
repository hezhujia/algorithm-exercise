package com.algrothm.exercise.dynamic;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.List;

public class MinimumTotal {

    public static int minimumTotal(List<List<Integer>> triangle) {

        for (int i = triangle.size()-2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            List<Integer> lastRow = triangle.get(i+1);
            for (int j = 0; j < row.size(); j++) {
                row.set(j, Math.min(lastRow.get(j), lastRow.get(j+1))+ row.get(j));
            }
        }

        return triangle.get(0).get(0);
    }

    static class TempTestCase implements TestCase<Integer> {
        List<List<Integer>> testTriangle;
        int exceptedResult;

        TempTestCase(List<List<Integer>> testTriangle, int exceptedResult) {
            this.testTriangle = testTriangle;
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Integer getExceptedResult() {
            return exceptedResult;
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase(ParseArgs.changeStringToTwoDimensionIntList("[[2],[3,4],[6,5,7],[4,1,8,3]]"), 11))
                .build();

        ExecTestCases.exec(testCases, tempTestCase -> minimumTotal(tempTestCase.testTriangle));
    }
}
