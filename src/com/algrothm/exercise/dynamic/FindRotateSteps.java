package com.algrothm.exercise.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FindRotateSteps {
    public static int findRotateSteps(String ring, String key) {
        List<Integer>[] positions = new List[26];
        for (int i = 0; i < 26; i++) {
            positions[i] = new ArrayList<>();
        }
        for (int i = 0; i < ring.length(); i++) {
            positions[ring.charAt(i)-'a'].add(i);
        }

        int[][] dp = new int[key.length()][ring.length()];

        for (int i = 0; i < key.length(); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            int curChar = key.charAt(i)-'a';
            if (i == 0) {
                for (int j = 0; j < positions[curChar].size(); j++) {
                    dp[i][positions[curChar].get(j)] = findMinPosition(0, positions[curChar].get(j), ring.length()) + 1;
                }
            } else {
                int preChar = key.charAt(i-1)-'a';
                for (int j = 0; j < positions[curChar].size(); j++) {
                    int min = Integer.MAX_VALUE;
                    for (int preCharPosition: positions[preChar]) {
                        int cost = dp[i-1][preCharPosition] + findMinPosition(preCharPosition, positions[curChar].get(j), ring.length()) + 1;
                        if (cost < min) {
                            min = cost;
                        }
                    }
                    dp[i][positions[curChar].get(j)] = min;
                }
            }
        }

        return Arrays.stream(dp[key.length() - 1]).min().getAsInt();
    }

    private static int findMinPosition(int startPos, int endPos, int n) {
        return Math.min(Math.abs(startPos-endPos), n-Math.abs(startPos-endPos));
    }

    public static void main(String[] args) {
        System.out.println(findRotateSteps("godding", "gdi"));
    }
}
