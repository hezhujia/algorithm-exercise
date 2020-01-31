package com.algrothm.exercise.matchstring;

public class KMP {

    public int match(char[] primary, char[] pattern) {
        int n = primary.length;
        int m = pattern.length;

        int[] next = getNext(pattern, m);

        int j = 0;
        for (int i = 0; i < m; i++) {
            // 当匹配到坏字符时，滑动模式串
            // 当没有没有可匹配的最长前缀时，不再滑动
            while (j > 0 && primary[i] != pattern[j]) {
                j = next[j-1] + 1;
            }
            if (primary[i] == pattern[j]) {
                j++;
            }
            // 匹配成功，返回匹配到的主串的开始位置
            if (j == m) {
                return i-m+1;
            }
        }

        return -1;
    }

    // 找到前缀的最长匹配前缀子串的结尾字符的位置
    // 即对于一个前缀pattern[0, i]，找到位置ki，使 pattern[0, ki] = pattern[i-ki, i]
    // 上一个前缀pattern[0, i-1]，其最长匹配前缀子串为 pattern[0, k1] = pattern[i-1-k1, i-1]
    private int[] getNext(char[] pattern, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; i++) {

            // pattern[0, ki-1] / pattern[i-ki, i-1] （即除了pattern[i]元素外的子串）
            // 一定是 pattern[0, k1] 的前缀子串
            // 由于 pattern[0, k1] = pattern[i-1-k1, i-1], 那么pattern[0, k1]的最长匹配前缀子串 pattern[0, x] 一定等于 pattern[y, i-1]
            // 即可通过查找 pattern[0, k1]的最长匹配前缀子串，来找到pattern[0, i-1]的次长匹配前缀子串
            while (k != -1 && pattern[k+1] != pattern[i]) {
                k = next[k];
            }

            if (pattern[k+1] == pattern[i]) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }

    public static void main(String[] args) {
        char[] primary = "ababaeabac".toCharArray();
        char[] pattern = "ababacd".toCharArray();

        KMP kmp = new KMP();
        System.out.println(kmp.match(primary, pattern));
    }
}
