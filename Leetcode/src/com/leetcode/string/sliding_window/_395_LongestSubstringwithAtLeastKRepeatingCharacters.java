package com.leetcode.string.sliding_window;

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
            int val = s.charAt(i) - 'a';
            if (count[val] > 0 && count[val] < k) {
                fullString = false;
            }
        }
        if (fullString) return s.length();

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

    // 更加暴力的解法，相当于直接验证有多少个组成。
    // time: O(26 * len(s)) space:O(26)
    public int longestSubstring2(String s, int k) {
        int res = 0, n = s.length();
        // 结果集里面最多包含1-26种字符、
        for (int count = 1; count <= 26; count++) {
            int start = 0;
            int end = 0;
            int uniqueCount = 0;
            int[] counter = new int[26];
            while (end < n) {
                boolean valid = true;
                if (counter[s.charAt(end) - 'a']++ == 0) uniqueCount++;
                while (uniqueCount > count) {
                    counter[s.charAt(start) - 'a']--;
                    if (counter[s.charAt(start) - 'a'] == 0) uniqueCount--;
                    start++;
                }
                for (int i = 0; i < 26; i++) {
                    if (counter[i] > 0 && counter[i] < k) valid = false;
                }
                if (valid) res = Math.max(res, end - start + 1);
                end++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        longestSubstring("abcabba", 2);
    }
}
