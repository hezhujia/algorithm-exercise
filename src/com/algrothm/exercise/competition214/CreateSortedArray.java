package com.algrothm.exercise.competition214;

import java.util.ArrayList;
import java.util.List;

public class CreateSortedArray {

    public static int createSortedArray(int[] instructions) {
        long result = 0;

        List<Integer> sortedArray = new ArrayList<>();
        sortedArray.add(instructions[0]);

        for (int i = 1; i < instructions.length; i++) {
            int nextNum = instructions[i];
            int minIndex = findMinNum(sortedArray, nextNum, 0, sortedArray.size()-1);
            int maxIndex = findMaxNum(sortedArray, nextNum, minIndex, sortedArray.size()-1);
            result += Math.min(minIndex+1, sortedArray.size()-maxIndex);
            sortedArray.add(minIndex+1, nextNum);
        }

        return (int) (result%1000000007);
    }

    private static int findMinNum(List<Integer> sortedArray, int num, int start, int end) {
        if (start == end) {
            if (sortedArray.get(start) >= num) {
                return start-1;
            } else {
                return start;
            }
        }
        if (end - start == 1) {
            if (sortedArray.get(end) < num) {
                return end;
            }
            if (sortedArray.get(start) < num) {
                return start;
            }
            return start-1;
        }

        int mid = (start + end) / 2;
        if (sortedArray.get(mid) >= num) {
            return findMinNum(sortedArray, num, start, mid-1);
        } else {
            return findMinNum(sortedArray, num, mid, end);
        }
    }

    private static int findMaxNum(List<Integer> sortedArray, int num, int start, int end) {
        if (start == end) {
            if (sortedArray.get(start) <= num) {
                return end+1;
            } else {
                return end;
            }
        }
        if (end - start == 1) {
            if (sortedArray.get(start) > num) {
                return start;
            }
            if (sortedArray.get(end) > num) {
                return end;
            }
            return end+1;
        }

        int mid = (start + end) / 2;
        if (sortedArray.get(mid) <= num) {
            return findMaxNum(sortedArray, num, mid+1, end);
        } else {
            return findMaxNum(sortedArray, num, start, mid);
        }
    }

    public static void main(String[] args) {
        System.out.println(createSortedArray(new int[]{4,14,10,2,5,3,8,19,7,20,12,1,9,15,13,11,18,6,16,17}));
    }
}
