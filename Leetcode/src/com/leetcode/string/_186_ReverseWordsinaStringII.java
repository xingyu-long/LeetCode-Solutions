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
        int r = 0;
        while (r < s.length) {
            int l = r;
            // 找空格的位置
            while (r < s.length && s[r] != ' ') {
                r++;
            }
            reverse(s, l, r - 1);
            r++;
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

    public static void reverseWords2(char[] s) {
        if (s == null || s.length == 0) return;
        reverse(s, 0, s.length - 1);
        int end = 0;
        System.out.println(end);
        while (end < s.length) {
            int start = end;
            while (end < s.length && s[end] != ' ') {
                end++;
            }
            reverse(s, start, end - 1);
            end++; // 为了下一个开始。
        }
    }
    public static void main(String[] args) {
        char[] s = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        System.out.println(Arrays.toString(s));
        reverseWords2(s);
        System.out.println(Arrays.toString(s));
    }
}
