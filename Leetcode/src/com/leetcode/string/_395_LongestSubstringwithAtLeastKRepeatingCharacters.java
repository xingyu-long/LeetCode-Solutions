package com.leetcode.string;

public class _395_LongestSubstringwithAtLeastKRepeatingCharacters {

    /**
     *  395. Longest Substring with At Least K Repeating Characters
     *  When: 2019/7/17
     *  Difficulty: Medium
     *
     * @param s
     * @param k
     * @return
     */
    //time:O(n) space:O(n)
    public static int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        boolean fullString = true;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] > 0
                    && count[s.charAt(i) - 'a'] < k) {
                fullString = false;
            }
        }
        if (fullString == true) return s.length();

        int begin = 0, end = 0, res = 0;
        while (end < s.length()) {
            if (count[s.charAt(end) - 'a'] < k) {
                // 前面小于k的情况进入递归之后返回是0，因为最后都是变成""；
                res = Math.max(res, longestSubstring(s.substring(begin, end), k));
                begin = end + 1;//相当于验证完前面的部分，然后begin就从之前的end开始
            }
            end++;
        }
        res = Math.max(res, longestSubstring(s.substring(begin), k));
        return res;
    }

    public static void main(String[] args) {
        longestSubstring("abcabba", 2);
    }
}
