package com.algrothm.exercise.dynamic;

public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
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

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1}};
        System.out.println(minPathSum(grid));
    }
}
