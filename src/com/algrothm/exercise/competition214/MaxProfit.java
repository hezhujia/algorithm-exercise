package com.algrothm.exercise.competition214;

import java.util.Arrays;

public class MaxProfit {
    public static int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        long result = 0;

        for (int i = inventory.length - 1; i >= 0; i--) {
            int n = inventory[i];
            int m = inventory.length - i;
            if (i != 0) {
                n = inventory[i] - inventory[i-1];
            }
            // 取全部
            if (orders > n * m) {
                orders = orders - n * m;
                result += ((long)inventory[i]-n+1 + (long) inventory[i]) * n / 2 * m;
            } else {
                // 中间就满足了
                int countAll = orders / m;
                int mol = orders % m;
                result += ((long) inventory[i]-countAll+1 + (long) inventory[i]) * countAll / 2 * m;
                result += ((long) inventory[i]-countAll) * mol;
                break;
            }

        }

        return (int) (result%1000000007);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1000000000}, 1000000000));
    }
}
