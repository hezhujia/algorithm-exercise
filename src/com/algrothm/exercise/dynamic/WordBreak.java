package com.algrothm.exercise.dynamic;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] isWordBreak = new boolean[s.length()+1];
        Set<String> dict = new HashSet<>(wordDict);
        for (int i = 0; i <= s.length(); i++) {
            if (dict.contains(s.substring(0, i))) {
                isWordBreak[i] = true;
            }
        }

        for (int i = 1; i < s.length(); i++) {
            if (!isWordBreak[i]) {
                continue;
            }
            for (int j = i+1; j <= s.length(); j++) {
                if (dict.contains(s.substring(i, j))) {
                    isWordBreak[j] = isWordBreak[i];
                }
            }
        }

        return isWordBreak[s.length()];
    }

    static class TempTestCase implements TestCase<Boolean> {

        String testS;
        List<String> testWordDict;
        boolean exceptedResult;

        TempTestCase(String testS, List<String> testWordDict, boolean exceptedResult) {
            this.testS = testS;
            this.testWordDict = testWordDict;
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Boolean getExceptedResult() {
            return exceptedResult;
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("a", List.of("a"), true))
                .addCase(new TempTestCase("applepenapple", List.of("apple", "pen"), true))
                .addCase(new TempTestCase("leetcode", List.of("leet", "code"), true))
                .addCase(new TempTestCase("catsandog", List.of("cats", "dog", "sand", "and", "cat"), false))
                .addCase(new TempTestCase("catssanddog", List.of("cats", "dog", "sand", "and", "cat"), true))
                .build();

        ExecTestCases.exec(testCases, tempTestCase -> wordBreak(tempTestCase.testS, tempTestCase.testWordDict));
    }
}
