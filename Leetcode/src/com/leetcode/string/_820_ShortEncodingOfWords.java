/*
 * @Date: 03/06/2021 10:48:52
 * @LastEditTime: 03/06/2021 10:49:30
 * @Description: String, Trie
 */
package com.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _820_ShortEncodingOfWords {
    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>(Arrays.asList(words));
        // check all suffix.
        for (String w : words) {
            for (int i = 1; i < w.length(); i++) {
                set.remove(w.substring(i));
            }
        }
        int res = 0;
        for (String w : set) {
            res += w.length() + 1;
        }
        return res;
    }
}
