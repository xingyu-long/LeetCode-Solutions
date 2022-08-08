package com.leetcode.string.sliding_window;

public class _340_LongestSubstringwithAtMostKDistinctCharacters {

    /**
     * 340. Longest Substring with At Most K Distinct Characters
     * When:11/3/2019
     * Difficulty: Medium
     * @param s
     * @param k
     * @return
     */
    // 可以算出最长长度和对应的字符串
    public static String getStringNoMoreTwoDistinct(String s, int k) {
        if (s == null || s.length() == 0) return "";
        int[] counter = new int[128];
        int end = 0;
        int start = 0;
        int num = 0;
        int max = 0;
        String res = "";
        while (end < s.length()) {
            if (counter[s.charAt(end)]++ == 0) num++;
            while (num > k) {
                counter[s.charAt(start)]--;
                if (counter[s.charAt(start)] == 0) num--;
                start++;
            }
            end++;
            if (end - start > max) {
                max = end - start;
                res = s.substring(start, end);
            }
        }
        return res;
    }
}
