package com.leetcode.string;

import java.util.HashMap;

public class _159_LongestSubstringwithAtMostTwoDistinctCharacters {

    /**
     *  159. Longest Substring with At Most Two Distinct Characters
     *  When:2019/7/22

     Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

     For example, Given s = “eceba”,

     T is "ece" which its length is 3.

     sliding window

     “eceba”

     *  Solution: 利用sliding window 主要记录的最新位置
     * @param s
     * @return
     */

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        int res = 0;
        while (end < s.length()) {
            if (map.size() <= 2) {
                map.put(s.charAt(end), end);
                end++;
            }
            if (map.size() > 2) {
                int leftMost = s.length();
                for (int num : map.values()) {
                    leftMost = Math.min(leftMost, num);
                }
                map.remove(s.charAt(leftMost));
                start = leftMost + 1;
            }
            // 这里不用加1 是因为前面每次操作之后end已经加1
            res = Math.max(res, end - start);
        }
        return res;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstringTwoDistinct("ccaabb");
    }
}