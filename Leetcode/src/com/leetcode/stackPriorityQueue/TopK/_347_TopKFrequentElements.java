package com.leetcode.stackPriorityQueue.TopK;

import java.util.*;

/**
 * @Date: 05/04/2020
 * @Description: TopK, PQ
 **/
public class _347_TopKFrequentElements {
    // 利用PriorityQueue
    // 这才是nlogK的算法 维持最多k个
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a).compareTo(map.get(b)));
        for (int key : map.keySet()) {
            pq.offer(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            list.add(pq.poll());
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(list.size() - i - 1);
        }
        return res;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int n = nums.length;
        List<Integer>[] bucket = new List[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(num);
        }
        int[] res = new int[k];
        int index = 0;
        for (int i = n; i >= 0; i--) {
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size() && k > 0; j++) {
                    res[index++] = bucket[i].get(j);
                    k--;
                }
            }
        }
        return res;
    }
}
