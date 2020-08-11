package com.leetcode.string.StrigSearch;

public class _1392_LongestHappyPrefix {
    // 找到结尾的数，即表示相同最大前缀和最大后缀和。
    // time:O(len(s))
    public String longestPrefix(String s) {
        KMP kmp = new KMP();
        int[] next = kmp.build(s);
        int n = next.length;
        return s.substring(0, next[n - 1]);
    }
}
