package com.algrothm.exercise.array;

import com.algrothm.exercise.utils.ParseArgs;

public class IslandPerimeter {

    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int count = 0;
                    if (i != 0) {
                        count += grid[i-1][j];
                    }
                    if (i != grid.length -1) {
                        count += grid[i+1][j];
                    }
                    if (j != 0) {
                        count += grid[i][j-1];
                    }
                    if (j != grid[0].length - 1) {
                        count += grid[i][j+1];
                    }
                    perimeter += 4 - count;
                }
            }
        }

        return perimeter;
    }

    public static void main(String[] args) {
        System.out.println(islandPerimeter(ParseArgs.changeStringToTwoDimensionIntArray("[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]")));
    }

}
