package com.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 30. Substring with Concatenation of All Words 12/7
 */
public class _30_SubstringwithConcatenationofAllWords {
    // 这个更像是内部的字符串匹配问题
    public List<Integer> findSubstring(String s, String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        if (s == null || s.length() == 0) return new ArrayList<>();
        HashMap<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        int total = s.length();
        int num = words.length;
        int len = words[0].length();
        // 相当于每次访问完num * len 的长度
        for (int i = 0; i < total - num * len + 1; i++) {
            HashMap<String, Integer> seen = new HashMap<>();
            int j;
            for (j = 0; j < num; j++) { // 遍历完全部单词
                String word = s.substring(i + j * len, i + (j + 1) * len);
                if (counter.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counter.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (j == num) res.add(i);
        }
        return res;
    }
}