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
    public static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 构造最大堆 这里的x.getValue() 就是hashMap对应的value
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
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
