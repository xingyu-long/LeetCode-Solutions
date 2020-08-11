package com.leetcode.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date: 05/10/2020
 * @Description: DP, word break
 **/
public class _472_ConcatenatedWords {

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
}
