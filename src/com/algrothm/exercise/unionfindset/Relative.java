package com.algrothm.exercise.unionfindset;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.Arrays;
import java.util.List;

public class Relative {

    public static boolean[] relative(int n, int[][] m, int[][] p) {
        boolean[] result = new boolean[p.length];
        int[] relatives = new int[n+1];

        for (int i = 1; i <= n; i++) {
            relatives[i] = i;
        }

        for (int[] ints : m) {
            merge(ints[0], ints[1], relatives);
        }

        for (int i = 0; i < p.length; i++) {
            result[i] = findRoot(p[i][0], relatives) == findRoot(p[i][1], relatives);
        }

        return result;
    }

    private static void merge(int r1, int r2, int[] relatives) {
        int root1 = findRoot(r1, relatives);
        int root2 = findRoot(r2, relatives);

        if (root1 != root2) {
            relatives[root1] = root2;
        }
    }

    private static int findRoot(int r, int[] relatives) {
        while (relatives[r] != r) {
            r = relatives[r];
        }
        return r;
    }

    static class TempClassCase implements TestCase<boolean[]> {

        int testN;
        int[][] testM;
        int[][] testP;
        boolean[] exceptedResult;

        TempClassCase(int testN, String testM, String testP, boolean[] exceptedResult) {
            this.testN = testN;
            this.testM = ParseArgs.changeStringToTwoDimensionIntArray(testM);
            this.testP = ParseArgs.changeStringToTwoDimensionIntArray(testP);
            this.exceptedResult = exceptedResult;
        }

        @Override
        public boolean[] getExceptedResult() {
            return exceptedResult;
        }

        @Override
        public String resultToString(boolean[] booleans) {
            return Arrays.toString(booleans);
        }
    }

    public static void main(String[] args) {
        List<TempClassCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempClassCase>()
                .addCase(new TempClassCase(6, "[[1,2],[1,5],[3,4],[5,2],[1,3]]", "[[1,4],[2,3],[5,6]]", new boolean[]{true, true, false}))
                .build();

        ExecTestCases.exec(testCases, tempClassCase -> relative(tempClassCase.testN, tempClassCase.testM, tempClassCase.testP), Arrays::compare);
    }

}
