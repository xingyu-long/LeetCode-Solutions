package com.leetcode.stack_priority_queue.TopK;

import java.util.*;

/**
 * @Date: 05/04/2020
 * @Description: TopK, PQ
 **/
public class _692_TopKFrequentWords {

    // 利用bucket sort
    // worse case: nlogn 刚好都在一个bucket里面。
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        List<String>[] buckets = new List[words.length + 1];
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
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

    // time:O(nlogk) 构造最小堆，最后reverse一下
    public List<String> topKFrequent2(String[] words, int k) {
        List<String> res = new LinkedList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        // O(n)
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (!map.get(a).equals(map.get(b))) {
                return map.get(a) - map.get(b);
            }
            return b.compareTo(a); // reverse
        });
        // 保持k个
        for (String key : map.keySet()) {
            pq.offer(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
