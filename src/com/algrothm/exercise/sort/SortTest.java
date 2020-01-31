package com.algrothm.exercise.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class SortTest {

    public static void test(Sort sort, int[] testArray) {
        Instant startTime = Instant.now();
        int[] sortedArray = sort.sort(testArray);
        Instant endTime = Instant.now();
        System.out.printf("Sort strategy [%s] cost [%s]ns for array size [%s]\n", sort.getClass().getName(),
                Duration.between(startTime, endTime).toNanos(), testArray.length);
        System.out.printf("Sort strategy [%s] sorting result is:\n [%s]\n", sort.getClass().getName(),
                Arrays.toString(sortedArray));
    }

    public static int[] createTestArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] testArray = createTestArray(50);
        test(new BobbleSort(), testArray.clone());
        test(new SelectSort(), testArray.clone());
        test(new InsertSort(), testArray.clone());
        test(new MergeSort(), testArray.clone());
        test(new QuickSort(), testArray.clone());
    }
}
