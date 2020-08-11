package com.leetcode.stackPriorityQueue;

import java.util.PriorityQueue;

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
}