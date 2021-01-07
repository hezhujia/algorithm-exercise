package com.algrothm.exercise.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParseArgs {

    public static int[][] changeStringToTwoDimensionIntArray(String str) {
        if (str.equals("[[]]")) {
            return new int[][]{};
        }
        String[] rows = str.split("],");
        int[][] results = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] item = rows[i].replaceAll("\\[", "").replaceAll("]", "").split(",");
            results[i] = new int[item.length];
            for (int j = 0; j < item.length; j++) {
                results[i][j] = Integer.parseInt(item[j]);
            }
        }

        return results;
    }

    public static String changeTwoDimensionIntArrayToString(int[][] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int[] row : array) {
            stringBuilder.append("[");
            for (int item : row) {
                stringBuilder.append(item).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1)
                    .append("]")
                    .append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1).append("]");

        return stringBuilder.toString();
    }

    public static List<List<Integer>> changeStringToTwoDimensionIntList(String str) {

        int[][] array = changeStringToTwoDimensionIntArray(str);
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : array) {
            List<Integer> newRow = new ArrayList<>();
            for (int item : row) {
                newRow.add(item);
            }
            result.add(newRow);
        }

        return result;
    }
}
