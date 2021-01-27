package com.algrothm.exercise.dynamic;

import java.util.*;

public class WordBreakTwo {

    // 内存优化
    public static List<String> wordBreak(String s, List<String> wordDict) {
        boolean[] isWordBreak = new boolean[s.length()+1];
        boolean[][] isWord = new boolean[s.length()+1][s.length()+1];
        Set<String> dictSet = new HashSet<>(wordDict);

        isWordBreak[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i-1; j >= 0; j--) {
                if (isWordBreak[j] && dictSet.contains(s.substring(j, i))) {
                    isWordBreak[i] = true;
                    isWord[j][i] = true;
                }
            }
        }

        if (!isWordBreak[s.length()]) {
            return new ArrayList<>();
        } else {
            return DSP(s, isWord, 0);
        }
    }

    public static List<String> DSP(String s, boolean[][] isWord, int startLength) {
        List<String> newResult = new ArrayList<>();
        for (int i = startLength; i <= s.length(); i++) {
            if (isWord[startLength][i]) {
                if (i == s.length()) {
                    newResult.add(s.substring(startLength, i));
                } else {
                    List<String> result = DSP(s, isWord, i);
                    for (String string : result) {
                        newResult.add(s.substring(startLength, i) + " " + string);
                    }
                }
            }
        }
        return newResult;
    }

    // 内存优化
    public static List<String> wordBreakTwo(String s, List<String> wordDict) {
        boolean[] isWordBreak = new boolean[s.length()+1];
        boolean[][] isWord = new boolean[s.length()+1][s.length()+1];
        Set<String> dictSet = new HashSet<>(wordDict);

        isWordBreak[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i-1; j >= 0; j--) {
                if (isWordBreak[j] && dictSet.contains(s.substring(j, i))) {
                    isWordBreak[i] = true;
                    isWord[j][i] = true;
                }
            }
        }

        if (!isWordBreak[s.length()]) {
            return new ArrayList<>();
        } else {
            return DSPTwo(s, isWord, 0);
        }
    }

    public static List<String> DSPTwo(String s, boolean[][] isWord, int startLength) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i <= s.length(); i++) {
            if (isWord[0][i]) {
                stack.push(s.substring(0, i));
            }
        }

        while (!stack.isEmpty()) {
            String curSubStr = stack.pop();
            int startIndex = curSubStr.replaceAll(" ", "").length() - 1;
            for (int i = startIndex; i <= s.length(); i++) {
                if (isWord[startIndex][i]) {
                    stack.push(curSubStr + " " + s.substring(startIndex, i));
                }
            }
        }
        List<String> newResult = new ArrayList<>();
        for (int i = startLength; i <= s.length(); i++) {
            if (isWord[startLength][i]) {
                if (i == s.length()) {
                    newResult.add(s.substring(startLength, i));
                } else {
                    List<String> result = DSP(s, isWord, i);
                    for (String string : result) {
                        newResult.add(s.substring(startLength, i) + " " + string);
                    }
                }
            }
        }
        return newResult;
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog")));
        System.out.println(wordBreak("pineapplepenapple", List.of("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }
}
