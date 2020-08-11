package com.leetcode.stackPriorityQueue;

import java.util.*;

public class _347_TopKFrequentElements {
    /**
     * 347. Top K Frequent Elements
     * When:2019/
     * @param nums
     * @param k
     * @return
     */
    // 利用PriorityQueue
    // 这才是nlogK的算法 维持最多k个
    public static List<Integer> topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : pq) {
            res.add(entry.getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        int k = 2;
        List<Integer> res = topKFrequent(nums, k);
        for (Integer out : res) {
            System.out.println(out);
        }
    }
}
