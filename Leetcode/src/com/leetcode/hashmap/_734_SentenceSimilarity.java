package com.leetcode.hashmap;

import java.util.HashMap;
import java.util.HashSet;

public class _734_SentenceSimilarity {

    // 两个长度不同肯定不会相似
    // map不包含word1里面的某个单词 return false
    // word1的边无法达到word2 return false
    // time:O(|pairs| + |words|) space: O(|pairs|)
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) map.put(pair[0], new HashSet<>());
            if (!map.containsKey(pair[1])) map.put(pair[1], new HashSet<>());
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (!map.containsKey(words1[i])) return false;
            if (!map.get(words1[i]).contains(words2[i])) return false;
        }
        return true;
    }
}
