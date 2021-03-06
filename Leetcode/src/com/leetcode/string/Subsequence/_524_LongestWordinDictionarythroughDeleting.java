/*
 * @Date: 01/13/2020 16:01:44
 * @LastEditTime: 02/22/2021 09:52:53
 * @Description: String, Subsequence
 */
package com.leetcode.string.Subsequence;

import java.util.List;

public class _524_LongestWordinDictionarythroughDeleting {
    // time:O(n * len(word))
    // 并不需要排序来帮忙
    public String findLongestWord2(String s, List<String> d) {
        if (d == null || d.size() == 0)
            return "";
        String res = "";
        for (String word : d) {
            if (isValid(s, word)) {
                if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0))
                    res = word;
            }
        }
        return res;
    }

    public boolean isValid(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == t.length();
    }

}
