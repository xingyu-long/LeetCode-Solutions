package com.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 10/28/2020
 * @Description:  依次构造即可
 **/
public class _720_Longest_Word_in_Dictionary {
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        String res = "";
        int n = words.length;
        Arrays.sort(words);
        Set<String> built = new HashSet<>();
        for (String word : words) {
            if (word.length() == 1 || built.contains(word.substring(0, word.length() - 1))) {
                res = word.length() > res.length() ? word : res;
                built.add(word);
            }
        }
        return res;
    }
}
