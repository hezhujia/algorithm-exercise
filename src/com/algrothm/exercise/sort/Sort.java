package com.algrothm.exercise.sort;

public interface Sort {
    int[] sort(int[] arrays);

    default void swap(int[] arrays, int a1, int a2) {
        int temp = arrays[a1];
        arrays[a1] = arrays[a2];
        arrays[a2] = temp;
    }
}
