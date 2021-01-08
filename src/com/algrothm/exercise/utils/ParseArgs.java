package com.algrothm.exercise.utils;

import java.util.ArrayList;
import java.util.List;

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

    public static char[][] changeStringToTwoDimensionCharArray(String str) {
        if (str.equals("[]") || str.equals("[[]]")) {
            return new char[][]{};
        }
        String[] rows = str.split("],");
        char[][] results = new char[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] item = rows[i].replaceAll("\\[", "")
                    .replaceAll("]", "")
                    .replaceAll("\"", "")
                    .split(",");
            if (item.length == 1 && item[0].equals("")) {
                results[i] = new char[0];
                continue;
            }
            results[i] = new char[item.length];
            for (int j = 0; j < item.length; j++) {
                results[i][j] = item[j].charAt(0);
            }
        }

        return results;
    }

    public static String changeTwoDimensionCharArrayToString(char[][] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (char[] row : array) {
            stringBuilder.append("[");
            for (char item : row) {
                stringBuilder.append(item).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1)
                    .append("]")
                    .append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1).append("]");

        return stringBuilder.toString();
    }
}
