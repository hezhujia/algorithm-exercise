package com.algrothm.exercise.sort;

import java.util.Arrays;

public class MergeSort implements Sort {

    @Override
    public int[] sort(int[] arrays) {
        return partitionSort(arrays, 0, arrays.length-1);
    }

    private int[] partitionSort(int[] arrays, int start, int end) {
        if (start >= end) {
            return new int[]{arrays[start]};
        }
        if (end - start == 1) {
            if (arrays[start] >= arrays[end]) {
                return new int[]{arrays[end], arrays[start]};
            }
            return new int[]{arrays[start], arrays[end]};
        }

        int middle = (start + end) / 2;
        int[] array1 = partitionSort(arrays, start, middle);
        int[] array2 = partitionSort(arrays, middle+1, end);

        // merge
        int[] mergedArray = new int[end - start + 1];
        int array1I = 0;
        int array2I = 0;
        int i = 0;
        while (array1I < array1.length && array2I < array2.length) {
            if (array1[array1I] <= array2[array2I]) {
                mergedArray[i++] = array1[array1I++];
            } else {
                mergedArray[i++] = array2[array2I++];
            }
        }

        if (i != mergedArray.length && array1I != array1.length) {
            for (int j = i; j < mergedArray.length; j++, array1I++) {
                mergedArray[j] = array1[array1I];
            }
        }
        if (i != mergedArray.length && array2I != array2.length) {
            for (int j = i; j < mergedArray.length; j++, array2I++) {
                mergedArray[j] = array2[array2I];
            }
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        Sort sort = new MergeSort();
        int[] arrays = {5, 2, 1, 10, 60, 0};
        System.out.println(Arrays.toString(sort.sort(arrays)));
    }
}
