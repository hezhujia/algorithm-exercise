package com.algrothm.exercise.hashmap;

import java.util.HashMap;
import java.util.Map;

public class UniqueOccurrences {
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        boolean[] repeatable = new boolean[1001];
        boolean isUnique = true;

        for (int num : arr) {
            if (occurrences.containsKey(num)) {
                occurrences.put(num, occurrences.get(num)+1);
            } else {
                occurrences.put(num, 1);
            }
        }


        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            Integer value = entry.getValue();
            if (!repeatable[value]) {
                repeatable[value] = true;
            } else {
                isUnique = false;
                break;
            }
        }

        return isUnique;
    }

    public static void main(String[] args) {
        System.out.println(uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,10,0}));
    }
}
