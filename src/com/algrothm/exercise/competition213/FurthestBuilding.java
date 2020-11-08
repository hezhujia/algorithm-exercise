package com.algrothm.exercise.competition213;

public class FurthestBuilding {
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        int i = 1;
        for (; i < heights.length; i++) {
            if (heights[i] <= heights[i-1]) {
                continue;
            }
            if ((heights[i] - heights[i-1]) <= bricks) {
                bricks -= heights[i] - heights[i-1];
            } else if (ladders > 0) {
                ladders--;
            } else {
                // 不可达到下一个节点
                break;
            }
        }

        return i - 1;
    }

    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{14,3,19,3}, 17, 0));
    }
}
