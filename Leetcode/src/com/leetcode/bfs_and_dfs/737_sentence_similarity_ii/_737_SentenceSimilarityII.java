package com.leetcode.bfs_and_dfs;

import java.util.HashMap;
import java.util.HashSet;

public class _737_SentenceSimilarityII {
    HashMap<String, HashSet<String>> map;
    // union find和dfs都可以做。
    // time:O(|Pairs| * |words1|) space:O(|pairs|)
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        map = new HashMap<>();
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) map.put(pair[0], new HashSet<>());
            if (!map.containsKey(pair[1])) map.put(pair[1], new HashSet<>());
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < words1.length; i++) {
            if (!dfs(words1[i], words2[2], new HashSet<>())) return false;
        }
        return true;
    }

    public boolean dfs(String source, String destination, HashSet<String> visited) {
        if (source.equals(destination)) return true;
        visited.add(source);
        for (String adj : map.get(source)) {
            if (visited.contains(adj)) continue;
            if (dfs(adj, destination, visited)) return true;
        }
        return false;
    }
}