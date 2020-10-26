package com.algrothm.exercise.array;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.*;

public class SmallerNumbersThanCurrent {

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        // 记录原位置
        int[] results = new int[nums.length];
        // Map<num, positions>
        Map<Integer, List<Integer>> position = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (position.containsKey(nums[i])) {
                position.get(nums[i]).add(i);
            } else {
                List<Integer> numPos = new ArrayList<>();
                numPos.add(i);
                position.put(nums[i], numPos);
            }
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                for (Integer pos : position.get(nums[i])) {
                    results[pos] = 0;
                }
            } else if (nums[i] != nums[i-1]) {
                for (Integer pos : position.get(nums[i])) {
                    results[pos] = i;
                }
            }
        }

        return results;
    }

    // 计数排序
    public static int[] smallerNumbersThanCurrentTwo(int[] nums) {
        int[] results = new int[nums.length];
        int[] counts = new int[101];

        for (int num : nums) {
            counts[num]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i-1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                results[i] = 0;
            } else {
                results[i] = counts[nums[i]-1];
            }
        }

        return results;
    }

    static class TempTestCase implements TestCase<int[]> {

        int[] testArray;
        int[] exceptedResult;

        TempTestCase(int[] testArray, int[] exceptedResult) {
            this.testArray = testArray;
            this.exceptedResult = exceptedResult;
        }

        @Override
        public int[] getExceptedResult() {
            return exceptedResult;
        }

        @Override
        public String resultToString(int[] result) {
            return Arrays.toString(result);
        }

        @Override
        public String toString() {
            return "TempTestCase{" +
                    "testArray=" + Arrays.toString(testArray) +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase(new int[]{8,1,2,2,3}, new int[]{4,0,1,1,3}))
                .addCase(new TempTestCase(new int[]{6,5,4,8}, new int[]{2,1,0,3}))
                .addCase(new TempTestCase(new int[]{7,7,7,7}, new int[]{0,0,0,0}))
                .build();

        ExecTestCases.exec(testCases,
                tempTestCase -> smallerNumbersThanCurrentTwo(tempTestCase.testArray),
                Arrays::compare);
    }

}
