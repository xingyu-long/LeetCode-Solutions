/*
 * @Date: 08/11/2020 16:07:14
 * @LastEditTime: 06/06/2022 20:55:27
 * @Description: String, Reverse
 */
package com.leetcode.string;


public class _186_ReverseWordsinaStringII {

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
