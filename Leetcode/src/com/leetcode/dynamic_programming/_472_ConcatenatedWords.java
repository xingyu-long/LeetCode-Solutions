package com.leetcode.dynamic_programming;

import java.util.*;

/**
 * @Date: 05/10/2020
 * @Description: DP, word break
 **/
public class _472_ConcatenatedWords {

    // time: O((# of words) * (length of word) * (length of word))
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        List<String> res = new ArrayList<>();
        HashSet<String> prevSet = new HashSet<>();
        for (String word : words) {
            if (canForm(prevSet, word)) {
                res.add(word);
            }
            prevSet.add(word);
        }
        return res;
    }

    public boolean canForm(Set<String> set, String word) {
        if (set.isEmpty()) {
            return false;
        }
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

    // Top-down
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        // word break?
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Set<String> dict = new HashSet<>();
        for (String word : words) {
            dict.add(word);
        }
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        List<String> res = new ArrayList<>();
        int n = words.length;
        for (int i = n - 1; i > 0; i--) {
            String w = words[i];
            dict.remove(w);
            Map<Integer, Boolean> map = new HashMap<>();
            if (find(w, dict, 0, map)) {
                res.add(w);
            }
        }
        return res;
    }

    public boolean find(String w, Set<String> dict, int index, Map<Integer, Boolean> map) {
        if (index == w.length()) return true;
        if (map.containsKey(index)) return map.get(index);

        for (int end = index + 1; end <= w.length(); end++) {
            String temp = w.substring(index, end);
            if (dict.contains(temp) && find(w, dict, end, map)) {
                map.put(index, true);
                return true;
            }
        }
        map.put(index, false);
        return false;
    }
}
