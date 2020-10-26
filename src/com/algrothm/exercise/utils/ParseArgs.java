package com.algrothm.exercise.utils;

public class ParseArgs {

    public static int[][] changeStringToTwoDimensionIntArray(String str) {
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
}
