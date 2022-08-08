package com.leetcode.string.string_search;

public class _459_RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        /*
             0 1 2 3 4
             a b a b $
             0 0 0 1 2
                 a b a b
        */
        if (s == null || s.length() == 0) return false;
        KMP kmp = new KMP();
        int[] next = kmp.build(s);
        int n = s.length(); // 这里是取s的length
        return next[n] > 0 && n % (n - next[n]) == 0;
    }

}
