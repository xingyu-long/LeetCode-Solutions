package com.leetcode.dynamic_programming;

import java.util.HashSet;
import java.util.PriorityQueue;

public class _264_UglyNumberII {
    /**
     * 264.Ugly Number II
     * When:2019/8/12
     * Difficulty: Medium
     * @param n
     * @return
     */
    // time:O(nlogn) space:O(n)
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        pq.offer(Long.valueOf(1));
        set.add(Long.valueOf(1));
        while (!pq.isEmpty()) {
            long cur = pq.poll();
            if (--n == 0) return (int) cur;
            if (set.add(cur * 2)) pq.offer(cur * 2);
            if (set.add(cur * 3)) pq.offer(cur * 3);
            if (set.add(cur * 5)) pq.offer(cur * 5);
        }
        return -1;
    }

    // 利用动态规划 
    public int nthUglyNumber2(int n) {
        int index2 = 0, index3 = 0, index5 = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int nextMin = Math.min(Math.min(dp[index2] * 2, dp[index3] * 3), dp[index5] * 5);
            if (dp[index2] * 2 == nextMin) index2++;
            if (dp[index3] * 3 == nextMin) index3++;
            if (dp[index5] * 5 == nextMin) index5++;
            dp[i] = nextMin;
        }
        return dp[n - 1];
    } 
}
