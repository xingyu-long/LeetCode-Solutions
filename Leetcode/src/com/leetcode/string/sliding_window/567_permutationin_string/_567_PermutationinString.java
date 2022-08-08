package com.leetcode.string.sliding_window;

/**
 * @Date: 05/18/2020
 * @Description: Sliding Window
 **/
public class _567_PermutationinString {
    // time:O(n + m) space:O(1)
    public boolean checkInclusion(String s1, String s2) {
        // sliding window的题目
        int[] count = new int[26];
        for (char ch : s1.toCharArray()) {
            count[ch - 'a']++;
        }
        int len = s1.length();
        int start = 0, end = 0;
        int n = s2.length();
        while (end < n) {
            if (count[s2.charAt(end) - 'a']-- > 0) len--;
            while (len == 0) {
                if (s1.length() == end - start + 1) {
                    return true;
                }
                count[s2.charAt(start) - 'a']++;
                if (count[s2.charAt(start) - 'a'] > 0) len++;
                start++;
            }
            end++;
        }
        return false;
    }
}
