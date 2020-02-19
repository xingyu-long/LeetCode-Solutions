package com.leetcode.array;

import java.util.*;

public class _1342_ReduceArraySizetoTheHalf {
    // 利用hashmap
    // 1342. Reduce Array Size to The Half
    // time:O(nlogn) space:O(n)
    public int minSetSize(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> map  = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = arr.length;
        int half = n / 2;
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        int res = 0;
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> curr = pq.poll();
            res++;
            n -= curr.getValue();
            if (n <= half) break;
        }
        return res;
    }

    // 当然也可以使用counting sort，利用count作为list的index，ArrayList<Integer>[] list = new ArrayList[arr.length + 1];
    // time:O(n) space:O(n)
    public int minSetSize2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> map  = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] list = new ArrayList[arr.length + 1];
        for (int key : map.keySet()) {
            int count = map.get(key);
            if (list[count] == null) {
                list[count] = new ArrayList<Integer>();
            }
            list[count].add(key);
        }
        int res = 0;
        int count = 0;
        int n = arr.length;
        for (int i = arr.length; i >= 0; i--) {
            if (list[i] != null) {
                for (int num : list[i]) {
                    res++;
                    count += i;
                    if (count >= n / 2) return res;
                }
            }
        }
        return n;
    }
}
