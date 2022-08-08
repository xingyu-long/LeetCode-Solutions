package com.leetcode.string.sliding_window;

import java.util.HashMap;

public class _159_LongestSubstringwithAtMostTwoDistinctCharacters {

    /**
     * 159. Longest Substring with At Most Two Distinct Characters
     * When:2019/7/22
     * review1:2019/8/29
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

    // 模板 https://leetcode.com/problems/minimum-window-substring/discuss/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems
    public static int lengthOfLongestSubstringTwoDistinct2(String s) {
        int begin = 0;
        int[] count = new int[128];
        int res = 0;
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)]++ == 0) k++;
            while (k > 2) {
                // 表示值为1的时候删除才可以将 k减少一个
                if (count[s.charAt(begin++)]-- == 1) k--;
            }
            res = Math.max(res, i - begin + 1);
        }
        return res;
    }

    // 写成这样更好写。
    public static String getStringNoMoreTwoDistinct(String s) {
        if (s == null || s.length() == 0) return "";
        int[] counter = new int[128];
        int end = 0;
        int start = 0;
        int num = 0;
        int max = 0;
        String res = "";
        while (end < s.length()) {
            if (counter[s.charAt(end)]++ == 0) num++;
            while (num > 2) {
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

    public static void main(String[] args) {
        lengthOfLongestSubstringTwoDistinct("ccaabb");
    }
}