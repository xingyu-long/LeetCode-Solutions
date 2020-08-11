package com.leetcode.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Date: 05/22/2020
 * @Description: bucket sort
 **/
public class _451_SortCharactersByFrequency {

    // 利用bucket sort。
    // time:O(len(s))
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        // get the freq of char in s
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        List<Character>[] buckets = new List[s.length() + 1];
        // build the buckets
        for (char key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new LinkedList<>();
            }
            buckets[freq].add(key);
        }
        // we iterate from max len to zero and to see if we can add char in result.
        // 从出现次数最多的char开始 然后拼接。
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char ch : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(ch);
                    }
                }
            }
        }
        return sb.toString();
    }
}
