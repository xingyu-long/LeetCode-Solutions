package com.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Date: 09/03/2020
 * @Description: Greedy
 **/
public class _1338_ReduceArraySizetoTheHalf {
    // time: NlogN, N distinct letter + M.
    // 相当于每次取出最多的情况
    public int minSetSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        for (int val : map.values()) {
            pq.offer(val);
        }
        int res = 0;
        int n = arr.length;
        int count = n;
        while (count > n / 2 && !pq.isEmpty()) {
            count -= pq.poll();
            res++;
        }
        return res;
    }
}
