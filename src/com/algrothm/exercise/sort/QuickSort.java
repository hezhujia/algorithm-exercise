package com.algrothm.exercise.sort;

import java.util.Arrays;

public class QuickSort implements Sort {

    @Override
    public int[] sort(int[] arrays) {
        partition(arrays, 0, arrays.length-1);
        return arrays;
    }

    private void partition(int[] arrays, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = start;
        for (int j = start; j < end; j++) {
            if (arrays[j] < arrays[end]) {
                swap(arrays, i, j);
                i++;
            }
        }
        swap(arrays, i, end);

        partition(arrays, start, i-1);
        partition(arrays, i+1, end);
    }

    public static void main(String[] args) {
        Sort sort = new QuickSort();
        int[] arrays = {-2116631023, -2103093960, -2060260556, -2038028128, -2100586456, -2017664088, -2015209697, -2111138879};
        System.out.println(Arrays.toString(sort.sort(arrays)));
    }
}
