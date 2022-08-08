package com.leetcode.greedy;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * _1296_DivideArrayinSetsofKConsecutiveNumbers
 */
public class _1296_DivideArrayinSetsofKConsecutiveNumbers {

    // time:O(nlogn) space:O(n)
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (nums == null || n == 0)
            return false;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums)
            pq.offer(num);
        int count = k;
        while (!pq.isEmpty()) {
            count--;
            int cur = pq.poll();
            while (count > 0) { // remove the consecutive one
                if (!pq.remove(cur + 1))
                    return false;
                else {
                    cur += 1;
                    count--;
                }
            }
            if (count == 0)
                count = k;
        }
        return pq.isEmpty() && count == k;
    }

    // time:O(nlogn) space:O(n)
    public boolean isPossibleDivide2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i : map.keySet()) {
            if (map.get(i) > 0) {
                for (int j = k - 1; j >= 0; j--) {
                    if (map.getOrDefault(i + j, 0) < map.get(i)) return false;
                    map.put(i + j, map.get(i + j) - map.get(i));
                }
            }
        }
        return true;
    }
}