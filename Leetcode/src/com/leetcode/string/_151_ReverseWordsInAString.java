package com.leetcode.string;

/**
 * @Date: 2019/03/17, 2019/8/3, 07/14/2020
 * @Description: String, reverse
 **/
public class _151_ReverseWordsInAString {

    // time:O(n) space:O(n)
    public static String reverseWords(String s) {
        s = s.trim();
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        String[] words = s.split("\\s+");
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i] + " ");
        }
        // 最后再trim一次。
        return sb.toString().trim();
    }
}
