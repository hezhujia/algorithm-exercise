package com.algrothm.exercise.competition;

//你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
//
//一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
//
//请你返回从左上角走到右下角的最小 体力消耗值 。
public class Three {
    public static int minimumEffortPath(int[][] heights) {
        int rowLength = heights.length;
        int colLength = heights[0].length;

        boolean[][] isVisit = new boolean[rowLength][colLength];


        return 0;
    }
}
