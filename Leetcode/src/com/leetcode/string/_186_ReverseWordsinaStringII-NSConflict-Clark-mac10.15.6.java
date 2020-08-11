package com.leetcode.string;


/**
 * @Date: 2019/8/5, 07/15/2020
 * @Description: String, reverse
 **/
public class _186_ReverseWordsinaStringII {

    /**
     * 186. Reverse Words in a String II When:2019/8/5 Difficulty: Medium
     */
    // 先反转整个数组，然后根据空格来反转单词
    // time:O(n) space:O(1)
    public static void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        reverse(s, 0, s.length - 1);
        int start = 0;
        // 这里需要考虑最后那个情况，没有空格，但是也需要反转
        for (int i = 0; i <= s.length; i++) {
            if (i == s.length || s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
    }

    public static void reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i--] = s[j];
            s[j++] = temp;
        }
    }
}
