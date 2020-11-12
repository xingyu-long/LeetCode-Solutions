package com.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 06/23/2020
 * @Description: TODO
 **/
public class _676_ImplementMagicDictionary {
    // 之前用错了方法，不用构建trie，确实没有想到长度一致的放在一起，当时觉得要每次多个遍历就想用trie
    Map<Integer, List<String>> map;

    /** Initialize your data structure here. */
    public _676_ImplementMagicDictionary() {
        map = new HashMap<>();
    }

    /** Build a dictionary through a list of words */
    // time:len(word)
    public void buildDict(String[] dict) {
        for (String word : dict) {
            map.computeIfAbsent(word.length(), x -> new ArrayList<>()).add(word);
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    // time: words(same length) * length
    public boolean search(String word) {
        if (!map.containsKey(word.length())) return false;
        for (String candidate : map.get(word.length())) {
            int mismatch = 0;
            for (int i = 0; i < candidate.length(); i++) {
                if (word.charAt(i) != candidate.charAt(i)) {
                    if (++mismatch > 1) break;
                }
            }
            if (mismatch == 1) return true;
        }
        return false;
    }
}
