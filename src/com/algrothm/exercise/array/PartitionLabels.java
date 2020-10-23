package com.algrothm.exercise.array;

import java.util.*;

public class PartitionLabels {

    public static List<Integer> partitionLabels(String S) {
        List<Integer> results = new ArrayList<>();
        Map<Character, Integer> lastIndexOfChar = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            lastIndexOfChar.put(S.charAt(i), i);
        }

        Integer subStrStart = 0;
        Integer subStrEnd = 0;
        for (int i = 0; i < S.length(); i++) {
            int j = lastIndexOfChar.get(S.charAt(i));
            if (j > subStrEnd) {
                subStrEnd = j;
            }
            // 一个子字符串扫描完成
            if (i == subStrEnd) {
                results.add(subStrEnd - subStrStart + 1);
                subStrStart = subStrEnd = i + 1;
            }
        }

        return results;
    }

    public static List<Integer> partitionLabelsTwo(String S) {
        List<Integer> results = new ArrayList<>();
        int[] lastIndexOfChar = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIndexOfChar[S.charAt(i) - 'a'] = i;
        }

        int subStrStart = 0;
        int subStrEnd = 0;
        for (int i = 0; i < S.length(); i++) {
            subStrEnd = Math.max(lastIndexOfChar[S.charAt(i) - 'a'], subStrEnd);
            // 一个子字符串扫描完成
            if (i == subStrEnd) {
                results.add(subStrEnd - subStrStart + 1);
                subStrStart = i + 1;
            }
        }

        return results;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabelsTwo("ababcbacadefegdehijhklij"));
    }
}
