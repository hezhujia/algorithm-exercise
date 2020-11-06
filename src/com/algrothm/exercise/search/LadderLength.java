package com.algrothm.exercise.search;

import java.util.*;
import java.util.stream.Collectors;

public class LadderLength {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> wordVisited = wordList.stream()
                .collect(Collectors.toMap(word -> word, word -> 0));
        Set<String> remainWord = new HashSet<>(wordList);

        if (!wordVisited.containsKey(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        wordVisited.put(beginWord, 1);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int indegree = wordVisited.get(word);

            // 修改每个单词，如果没改过，放queue中；如果有改过，跳过；如果找到endword，弹出；如果不能改，弹出
            Set<String> visitWord = new HashSet<>();
            for (String nextWord : remainWord) {
                if (canChange(word, nextWord)) {
                    if (nextWord.equals(endWord)) {
                        wordVisited.put(endWord, indegree+1);
                        queue.clear();
                        break;
                    }
                    queue.add(nextWord);
                    wordVisited.put(nextWord, indegree+1);
                    visitWord.add(nextWord);
                }
            }

            remainWord.removeAll(visitWord);
        }

        return wordVisited.get(endWord);
    }

    // 判断单词1变动一个字母能变成单词2
    private static boolean canChange(String word1, String word2) {
        boolean canChange = false;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (canChange) {
                    canChange = false;
                    break;
                } else {
                    canChange = true;
                }
            }
        }
        return canChange;
    }

    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log")));
    }
}
