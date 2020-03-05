package com.algrothm.exercise.dynamic;

import java.util.Arrays;

public class Coin {

    public int coinChange(int[] coins, int amount) {
        int[] priceStatus = new int[amount+1];
        Arrays.fill(priceStatus, amount+1);

        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                int preCoin = i - coin;
                if (preCoin > 0) {
                    priceStatus[i] = Math.min(priceStatus[preCoin], priceStatus[i]);
                }
            }
        }

        return priceStatus[amount] == (amount+1) ? -1 : priceStatus[amount];
    }

    public int solution(int weight) {
        int[] priceStatus = new int[weight+1];
        Arrays.fill(priceStatus, Integer.MAX_VALUE);

        priceStatus[1] = 1;
        priceStatus[3] = 1;
        priceStatus[5] = 1;

        for (int i = 2; i <= weight; i++) {
            if (i - 1 > 0 && priceStatus[i-1] != Integer.MAX_VALUE) {
                priceStatus[i] = Math.min(priceStatus[i-1] + 1, priceStatus[i]);
            }
            if (i - 3 > 0 && priceStatus[i-3] != Integer.MAX_VALUE) {
                priceStatus[i] = Math.min(priceStatus[i-3] + 1, priceStatus[i]);
            }
            if (i - 5 > 0 && priceStatus[i-5] != Integer.MAX_VALUE) {
                priceStatus[i] = Math.min(priceStatus[i-3] + 1, priceStatus[i]);
            }
        }

        if (priceStatus[weight] == 0) {
            return -1;
        } else {
            return priceStatus[weight];
        }
    }

    // 使用回溯法获取结果: 如果
    private int minCoin(int curWeight, int number, int weight) {
        if (curWeight > weight) {
            return Integer.MAX_VALUE;
        }
        if (curWeight == weight) {
            return number;
        }
        int min1 = minCoin(curWeight+1, number+1, weight);
        int min2 = minCoin(curWeight+3, number+1, weight);
        int min3 = minCoin(curWeight+5, number+1, weight);
        return Math.min(Math.min(min1, min2), min3);
    }



    public static void main(String[] args) {
        Coin coin = new Coin();
        int[] coins = {3};
        int amount = 2;
        System.out.println(coin.coinChange(coins, amount));
    }
}
