package com.leetcode.string.slidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 05/11/2020
 * @Description: Sliding Window
 **/
public class _438_FindAllAnagramsinaString {

    public List<Integer> findAnagrams(String s, String p) {
        // sliding window??
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
