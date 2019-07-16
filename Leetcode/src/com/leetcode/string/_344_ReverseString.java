package com.leetcode.string;

public class _344_ReverseString {
    /**
     *  344. Reverse String
     *  When:2019/7/16
     *
     */
    public void reverseString(char[] s) {
        exch(s, 0, s.length - 1);
    }
    public void exch(char[] s, int lo, int hi) {
        while (lo < hi) {
            char temp  = s[lo];
            s[lo] = s[hi];
            s[hi] = temp;
            lo++;
            hi--;
        }
    }
}
