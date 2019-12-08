package com.algrothm.exercise.sort;

import java.util.Arrays;

public class InsertSort implements Sort {

    @Override
    public int[] sort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int temp = arrays[i];
            int j = i;
            while (--j >= 0){
                if (arrays[j] > temp) {
                    arrays[j+1] = arrays[j];
                } else {
                    break;
                }
            }
            arrays[j+1] = temp;
        }
        return arrays;
    }

    public static void main(String[] args) {
        Sort sort = new InsertSort();
        int[] arrays = {5, 2, 1, 10, 60, 0};
        System.out.println(Arrays.toString(sort.sort(arrays)));
    }
}
