package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class _819_MostCommonWord {
    /**
     * 819. Most Common Word
     * time: 10/21/2019
     * Difficulty: Easy
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return "";
        String[] words = paragraph.toLowerCase().split("[\\W]+"); // 表示分割掉非单词的部分
        HashSet<String> set = new HashSet();
        for (String ban : banned) {
            set.add(ban);
        }
        String res = "";
        int max = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!set.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
                if (map.get(word) > max) {
                    max = map.get(word);
                    res = word;
                }
            }
        }
        return res;
    }
}
