package com.leetcode.string;

public class _186_ReverseWordsinaStringII {
    /**
     * 186. Reverse Words in a String II
     * When:2019/8/5
     * Difficulty: Medium
     */
    // 先反转整个数组，然后根据空格来反转单词
    // time:O(n) space:O(1)
    public void reverseWords(char[] s) {
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

    public void reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j++;
        }
    }
}
