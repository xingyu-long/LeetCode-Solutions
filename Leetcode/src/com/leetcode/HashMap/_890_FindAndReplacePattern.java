/*
 * @Date: 05/21/2021 09:29:06
 * @LastEditTime: 05/21/2021 09:30:34
 * @Description: HashMap
 */
package com.leetcode.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _890_FindAndReplacePattern {
    // time: (n * len(word))
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (isValid(word, pattern)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isValid(String word, String pattern) {
        if (word.length() != pattern.length())
            return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char ch1 = word.charAt(i), ch2 = pattern.charAt(i);
            if (map.containsKey(ch1)) {
                if (map.get(ch1) != ch2)
                    return false;
            } else {
                if (map.containsValue(ch2))
                    return false;
                else
                    map.put(ch1, ch2);
            }
        }
        return true;
    }
}
