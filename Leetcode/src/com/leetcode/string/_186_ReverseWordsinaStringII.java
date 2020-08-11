package com.leetcode.string;

import java.util.Arrays;

public class _186_ReverseWordsinaStringII {
    /**
     * 186. Reverse Words in a String II
     * When:2019/8/5
     * Difficulty: Medium
     */
    // 先反转整个数组，然后根据空格来反转单词
    // time:O(n) space:O(1)
    public static void reverseWords(char[] s) {
        if (s == null || s.length == 0) return;
        reverse(s, 0, s.length - 1);
        int l = 0;
        for (int i = 0; i <= s.length; i++) {
            if (i == s.length || s[i] == ' ') {
                reverse(s, l, i - 1);
                l = i + 1;
            }
        }
    }

    public static void reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {
        char[] s = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        System.out.println(Arrays.toString(s));
        reverseWords(s);
        System.out.println(Arrays.toString(s));
    }
}
