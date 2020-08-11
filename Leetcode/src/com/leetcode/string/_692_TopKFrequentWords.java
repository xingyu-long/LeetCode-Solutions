package com.leetcode.string;

import java.util.*;

public class _692_TopKFrequentWords {
    // 利用bucket sort
    // worse case: nlogn 刚好都在一个bucket里面。
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) return new ArrayList<>();
        List<String> [] buckets = new List[words.length + 1];
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(key);
        }
        int n = buckets.length;
        List<String> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                Collections.sort(buckets[i]);
                for (int j = 0; j < buckets[i].size() && k > 0; j++) {
                    res.add(buckets[i].get(j));
                    k--;
                }
            }
        }
        return res;
    }

    // time:O(nlogk) 构造最小堆，最后需要reverse结果（这里用linkedlist插到第0个位置。）
    public List<String> topKFrequent2(String[] words, int k) {
        if (words == null || words.length == 0) return new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 用最小堆
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }
}
