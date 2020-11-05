package com.algrothm.exercise.array;

import java.util.*;

public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        for (int num1: nums1){
            set1.add(num1);
        }

        for (int num2: nums2) {
            if (set1.contains(num2)) {
                result.add(num2);
                set1.remove(num2);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
