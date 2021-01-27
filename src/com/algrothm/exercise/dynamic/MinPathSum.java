package com.algrothm.exercise.dynamic;

import java.util.Arrays;

public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid[0].length;

        int[] curPath = new int[n];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < curPath.length; j++) {
                if (j == 0) {
                    curPath[j] = curPath[j] + grid[i][j];
                } else if (i == 0) {
                    curPath[j] = curPath[j-1] + grid[i][j];
                } else {
                    curPath[j] = Math.min(curPath[j], curPath[j-1]) + grid[i][j];
                }
            }
        }

        return curPath[curPath.length - 1];
    }

    // 动归+滚动数组
    public static int minPathSumByDP(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[] curPath = new int[grid[0].length];
        Arrays.fill(curPath, Integer.MAX_VALUE);
        curPath[0] = 0;

        for (int i = 0; i < grid.length; i++) {
            curPath[0] = curPath[0] + grid[i][0];
            for (int j = 1; j < grid[0].length; j++) {
                curPath[j] = Math.min(curPath[j-1], curPath[j]) + grid[i][j];
            }
        }

        return curPath[grid[0].length-1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1}};
        System.out.println(minPathSum(grid));
    }
}
