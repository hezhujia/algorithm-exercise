package com.algrothm.exercise.heap;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.*;
import java.util.stream.Collectors;

public class KClosest {

    public static int[][] kClosest(int[][] points, int K) {
        // 大顶堆
        int[][] heap = new int[K][2];
        heap[0] = points[0];

        for (int i = 1, j=1; i < points.length; i++) {
            if (j < K) {
                heap[j] = points[i];

                // 堆化
                downToUp(heap, j);
                j++;
            } else if (getDoubleDistance(heap[0]) > getDoubleDistance(points[i])) {
                heap[0] = points[i];
                // 从上到下堆化
                upToDown(heap);
            }
        }

        return heap;
    }

    private static int getDoubleDistance(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }

    // 自底向上
    private static void downToUp(int[][]heap, int index) {
        while ((index-1) / 2 >= 0) {
            if (getDoubleDistance(heap[(index-1)/2]) < getDoubleDistance(heap[index])) {
                int[] temp = heap[index];
                heap[index] = heap[(index-1)/2];
                heap[(index-1)/2] = temp;
                index = (index-1) / 2;
            } else {
                break;
            }
        }
    }

    // 自顶向下
    private static void upToDown(int[][] heap) {
        int i = 0;
        while ((i*2+1) < heap.length) {
            int maxChildDistance = getDoubleDistance(heap[i*2+1]);
            int maxChildIndex = i*2+1;
            // 存在右孩子
            if ((i*2+2) < heap.length) {
                if (getDoubleDistance(heap[i*2+2]) > maxChildDistance) {
                    maxChildDistance = getDoubleDistance(heap[i*2+2]);
                    maxChildIndex = i*2+2;
                }
            }
            if (getDoubleDistance(heap[i]) < maxChildDistance) {
                int[] temp = heap[i];
                heap[i] = heap[maxChildIndex];
                heap[maxChildIndex] = temp;
                i = maxChildIndex;
            } else {
                break;
            }
        }
    }

    static class TempTestCase implements TestCase<int[][]> {

        int[][] testPoints;
        int testK;
        int[][] exceptedResult;

        TempTestCase(String testPoints, int testK, String exceptedResult) {
            this.testPoints = ParseArgs.changeStringToTwoDimensionIntArray(testPoints);
            this.testK = testK;
            this.exceptedResult = ParseArgs.changeStringToTwoDimensionIntArray(exceptedResult);
        }

        @Override
        public int[][] getExceptedResult() {
            return exceptedResult;
        }

        @Override
        public String resultToString(int[][] result) {
            return ParseArgs.changeTwoDimensionIntArrayToString(result);
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("[[1,3],[-2,2]]", 1, "[[-2,2]]"))
                .addCase(new TempTestCase("[[3,3],[5,-1],[-2,4]]", 2, "[[3,3],[-2,4]]"))
                .addCase(new TempTestCase("[[1,0],[0,1]]", 2, "[[1,0],[0,1]]"))
                .addCase(new TempTestCase("[[6,10],[-3,3],[-2,5],[0,2]]",3, "[[0,2],[-3,3],[-2,5]]"))
                .addCase(new TempTestCase("[[10,-2],[2,-2],[10,10],[9,4],[-8,1]]", 4, "[[2,-2],[-8,1],[9,4],[10,-2]]"))
                .addCase(new TempTestCase("[[100,-16],[-31,45],[80,-47],[41,59],[-59,-34],[-34,-76],[-5,-77],[35,40],[58,-30],[31,-96],[40,14],[-25,50],[37,-38],[-54,-31]]",8, "[[40,14],[37,-38],[35,40],[-31,45],[-25,50],[-54,-31],[58,-30],[-59,-34]]"))
                .build();

        ExecTestCases.exec(testCases, tempTestCase -> kClosest(tempTestCase.testPoints, tempTestCase.testK),
                (result1, result2) -> {
                    Set<String> pairs = Arrays.stream(result1).map(item -> item[0] + "," + item[1]).collect(Collectors.toSet());
                    for (int[] pair: result2) {
                        String item = pair[0] + "," + pair[1];
                        if (!pairs.contains(item)) {
                            return -1;
                        }
                    }
                    return 0;
                });
    }

    private static void printDoubleDistance(String array) {
        int[][] points = ParseArgs.changeStringToTwoDimensionIntArray(array);
        for (int[] point : points) {
            System.out.println("[" + point[0] + "," + point[1] + "]: " + getDoubleDistance(point));
        }
    }
}
