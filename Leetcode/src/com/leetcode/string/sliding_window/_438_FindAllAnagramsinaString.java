/*
 * @Date: 05/11/2020 15:33:33
 * @LastEditTime: 06/08/2022 09:40:16
 * @Description: Sliding Window
 */
package com.leetcode.string.sliding_window;

import java.util.ArrayList;
import java.util.List;


public class _438_FindAllAnagramsinaString {

    // time: O(len(s) + len(p))
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        // "cbaebabacd" "abc"
        int start = 0, end = 0;
        int n = s.length();
        int len = p.length();
        int[] count = new int[26];
        for (char ch : p.toCharArray()) {
            count[ch - 'a']++;
        }
        while (end < n) {
            if (count[s.charAt(end) - 'a']-- > 0) {
                len--;
            }
            while (len == 0) {
                // find the potential candidates
                if (end - start + 1 == p.length()) {
                    res.add(start);
                }
                count[s.charAt(start) - 'a']++;
                if (count[s.charAt(start) - 'a'] > 0) {
                    len++;
                }
                start++;
            }
            end++;
        }
        return res;
    }
}
