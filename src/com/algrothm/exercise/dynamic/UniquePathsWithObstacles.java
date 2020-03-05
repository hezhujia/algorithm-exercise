package com.algrothm.exercise.dynamic;

import java.util.Arrays;

public class UniquePathsWithObstacles {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] curPath = new int[n];
        curPath[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前的路被堵了，值为0
                if (obstacleGrid[i][j] == 1) {
                    curPath[j] = 0;
                } else if (j != 0) {
                    curPath[j] = curPath[j-1] + curPath[j];
                }
            }
        }

        return curPath[n-1];
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{0}, {1}};
        System.out.println(uniquePathsWithObstacles(board));
    }
}
