package com.leetcode.string;

public class _344_ReverseString {
    /**
     *  344. Reverse String
     *  When:2019/7/16
     *  Review1:2019/7/23
     *  review2:2019/8/24
     *  Difficulty: Easy
     */
    //time:O(n) space:O(1)
    //iteration
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
    // time:O(n) space:O(1)
    // recursion way
    public void reverseString2(char[] s) {
        helper(s, 0, s.length - 1);
    }
    public void helper(char[] s, int start, int end) {
        if (start >= end) {
            return;
        }
        helper(s, start + 1, end - 1);
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
    }
}
