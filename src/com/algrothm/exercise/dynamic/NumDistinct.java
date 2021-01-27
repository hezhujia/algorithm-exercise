package com.algrothm.exercise.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumDistinct {

    // 暴力破解
    // 计算每个子字符串个数
    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        if (s.length() == t.length()) {
            return s.equals(t) ? 1 : 0;
        }

        Map<Character, List<Integer>> positionSChar = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!positionSChar.containsKey(s.charAt(i))) {
                positionSChar.put(s.charAt(i), new ArrayList<>());
            }
            positionSChar.get(s.charAt(i)).add(i);
        }

        List<List<Integer>> positions = positionSChar.get(t.charAt(0))
                .stream()
                .map(List::of)
                .collect(Collectors.toList());

        for (int n = 1; n < t.length(); n++) {
            char currentChar = t.charAt(n);

            List<List<Integer>> newPositions = new ArrayList<>();
            for (List<Integer> lastNPosPair : positions) {
                for (Integer curPos : positionSChar.get(currentChar)) {
                    boolean flag = true;
                    for (Integer lastNPos : lastNPosPair) {
                        if (curPos <= lastNPos) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        List<Integer> newPosition = new ArrayList<>(lastNPosPair);
                        newPosition.add(curPos);
                        newPositions.add(newPosition);
                    }
                }
            }
            positions = newPositions;
        }

        return positions.size();
    }

    // 动态规划
    public static int numDistinctByDP(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        if (s.length() == t.length()) {
            return s.equals(t) ? 1 : 0;
        }

        int[][] numDistinct = new int[s.length()+1][t.length()+1];

        for (int i = 0; i < s.length(); i++) {
            numDistinct[i][0] = 1;
            for (int j = 0; j < t.length(); j++) {
                numDistinct[i+1][j+1] = numDistinct[i][j+1];
                numDistinct[i+1][j+1] += s.charAt(i) == t.charAt(j) ? numDistinct[i][j] : 0;
            }
        }

        return numDistinct[s.length()][t.length()];
    }

    // 滚动数组
    public static int numDistinctByDPTwo(String s, String t) {
        int[] numDistinct = new int[t.length()+1];

        for (int i = 0; i < s.length(); i++) {
            numDistinct[0] = 1;
            for (int j = t.length()-1; j >= 0; j--) {
                numDistinct[j+1] = s.charAt(i) == t.charAt(j) ? numDistinct[j+1]+numDistinct[j] : numDistinct[j+1];
            }
        }

        return numDistinct[t.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDistinctByDPTwo("babgbag", "bag"));
        System.out.println(numDistinctByDPTwo("aabdbaabeeadcbbdedacbbeecbabebaeeecaeabaedadcbdbcdaabebdadbbaeabdadeaabbabbecebbebcaddaacccebeaeedababedeacdeaaaeeaecbe", "bddabdcae"));
    }
}
