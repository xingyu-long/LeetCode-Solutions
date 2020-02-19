package com.leetcode.string.Subsequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _524_LongestWordinDictionarythroughDeleting {
    // 524. Longest Word in Dictionary through Deleting
    // time:O(n * len(word) + mlogm)
    public String findLongestWord(String s, List<String> d) {
        if (d == null || d.size() == 0) return "";
        List<String> lists = new ArrayList<>();
        for (String word : d) {
            if (isValid(s, word)) lists.add(word);
        }
        if (lists.size() == 0) return "";
        Collections.sort(lists, (a, b) -> (a.length() - b.length() == 0 ? a.compareTo(b) : b.length() - a.length()));
        return lists.get(0);
    }

    // 不用sorting
    // time:O(n * len(word))
    public String findLongestWord2(String s, List<String> d) {
        if (d == null || d.size() == 0) return "";
        String res = "";
        List<String> lists = new ArrayList<>();
        for (String word : d) {
            if (isValid(s, word)) {
                if (word.length() > res.length() ||
                        (word.length() == res.length() && word.compareTo(res) < 0))
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
