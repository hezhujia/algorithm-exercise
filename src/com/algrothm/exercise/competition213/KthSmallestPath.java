package com.algrothm.exercise.competition213;

public class KthSmallestPath {

    //["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"]
    public static String kthSmallestPath(int[] destination, int k) {
        int V_num = destination[0];
        int H_num = destination[1];
        int[][] nums = new int[H_num+1][V_num+1];

        for (int i = 0; i <= H_num; i++) {
            for (int j = 0; j <= V_num; j++) {
                if (i == 0 || j == 0) {
                    nums[i][j] = 1;
                } else if (i == 1 || j == 1) {
                    nums[i][j] = i + j;
                } else {
                    nums[i][j] = nums[i - 1][j] + nums[i][j - 1];
                }
            }
        }

        StringBuilder result = new StringBuilder();

        int position = 0;
        int countH = H_num;
        int countV = V_num;

        while (countH != 0 && countV != 0) {
            // 找前面有多少个H
            for (int i = 0; i <= countH; i++) {
                int nextPosition = position + nums[i][countV];
                int prePosition = position;
                if (i != 0) {
                    prePosition = position + nums[i - 1][countV];
                }

                if (nextPosition == k) {
                    result.append("H".repeat(countH - i));
                    result.append("V".repeat(countV));
                    result.append("H".repeat(i));
                    countH = 0;
                    countV = 0;
                    break;
                }
                if (prePosition < k && nextPosition > k) {
                    if (countH != i) {
                        result.append("H".repeat(countH - i));
                        countH = i;
                    }
                    result.append("V");
                    countV--;
                    position = prePosition;
                    break;
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(kthSmallestPath(new int[]{2, 3}, 9));
    }
}
