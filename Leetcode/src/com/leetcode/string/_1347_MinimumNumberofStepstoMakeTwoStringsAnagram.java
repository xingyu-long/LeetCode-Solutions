package com.leetcode.string;

/**
 * @Date: 08/15/2020
 * @Description: String, Anagram.
 **/
public class _1347_MinimumNumberofStepstoMakeTwoStringsAnagram {
    // time: O(n)
    // 只需看两者的字符差的情况。
    public int minSteps(String s, String t) {
        if (s.length() != t.length()) {
            return 0;
        }

        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        for (char ch : t.toCharArray()) {
            count[ch - 'a']--;
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                res += count[i];
            }
        }
        return res;
    }
}
