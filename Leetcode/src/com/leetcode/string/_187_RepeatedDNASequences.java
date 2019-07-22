package com.leetcode.string;

import java.util.*;

public class _187_RepeatedDNASequences {


    /**
     *  187. Repeated DNA Sequences
     *  When:2019/7/22
     *  Difficulty: Medium
     *  Solution: hashSet
     * @param s
     * @return
     */
    // 利用hashmap
    // time: O(n) space:O(n)
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        // 暴力解法
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() - 10 + 1; i++) {
            String temp = s.substring(i, i + 10);
            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) + 1);
            } else {
                map.put(temp, 1);
            }
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > 1) {
                res.add(key);
            }
        }
        return res;
    }

    // 利用hashSet

    public List<String> findRepeatedDnaSequences2(String s) {
        HashSet<String> seen = new HashSet<>();
        HashSet<String> repeated = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String temp = s.substring(i, i + 10);
            if (!seen.add(temp)) {
                repeated.add(temp);
            }
        }
        return new ArrayList<>(repeated);
    }
}
