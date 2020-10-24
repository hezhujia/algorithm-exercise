package com.algrothm.exercise.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExecTestCases {

    private ExecTestCases(){};

    public static <T extends TestCase<R>, R> void exec(List<T> testCases, Function<T, R> function) {
        boolean isPass = true;
        for (T testcase :testCases) {
            R exceptedResult = testcase.getExceptedResult();
            R actualResult = function.apply((T) testcase);
            if (actualResult != exceptedResult) {
                isPass = false;
                System.out.println("test failed!");
                System.out.printf("for testCase:[%s] exceptedResult:[%s] actualResult:[%s]", testcase, exceptedResult, actualResult);
            }
        }
        if (isPass) {
            System.out.println("test success!");
        }
    }

    public static class TestCaseArrayBuilder<T> {
        private final List<T> testCases = new ArrayList<>();

        public TestCaseArrayBuilder<T> addCase(T testCase) {
            testCases.add(testCase);
            return this;
        }

        public List<T> build() {
            return testCases;
        }
    }
}
