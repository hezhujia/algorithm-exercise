package com.algrothm.exercise.competition213;

import com.algrothm.exercise.utils.ParseArgs;

public class CanFormArray {

    public static boolean canFormArray(int[] arr, int[][] pieces) {
        boolean result = true;
        boolean[] visitedPieces = new boolean[pieces.length];

        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            boolean findI = false;
            for (; j < pieces.length; j++) {
                if (!visitedPieces[j]) {
                    int k = 0;
                    for (; k < pieces[j].length; k++) {
                        if (arr[i+k] != pieces[j][k]) {
                            break;
                        }
                    }
                    // 匹配
                    if (k == pieces[j].length) {
                        visitedPieces[j] = true;
                        i += pieces[j].length - 1;
                        findI = true;
                        break;
                    }
                }
            }
            if (!findI) {
                result = false;
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(canFormArray(new int[]{49,18,16}, ParseArgs.changeStringToTwoDimensionIntArray("[[16,18,49]]")));
    }
}
