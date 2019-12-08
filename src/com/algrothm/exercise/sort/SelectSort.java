package com.algrothm.exercise.sort;

import java.util.Arrays;

public class SelectSort implements Sort {

    @Override
    public int[] sort(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < arrays.length; j++) {
                if (arrays[j] < arrays[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arrays[minIndex];
                arrays[minIndex] = arrays[i];
                arrays[i] = temp;
            }
        }
        return arrays;
    }

    public static void main(String[] args) {
        Sort sort = new SelectSort();
        int[] arrays = {5, 2, 1, 10, 60, 0};
        System.out.println(Arrays.toString(sort.sort(arrays)));
    }
}
