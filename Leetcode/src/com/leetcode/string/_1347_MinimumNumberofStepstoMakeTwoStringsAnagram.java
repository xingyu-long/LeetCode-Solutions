/*
 * @Date: 08/15/2020 15:20:41
 * @LastEditTime: 08/02/2022 21:20:57
 * @Description: String, Anagram.
 */
package com.leetcode.string;

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
