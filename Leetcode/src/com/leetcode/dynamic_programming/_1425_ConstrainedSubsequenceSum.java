package com.leetcode.dynamic_programming;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date: 04/30/2020
 * @Description: DP, Sliding Window Maximum
 **/
public class _1425_ConstrainedSubsequenceSum {
    // 保持单调递减的队列
    public int constrainedSubsetSum(int[] nums, int k) {
        // 参考subarray sum problem 和 那个239. Sliding Window Maximum
        int n = nums.length;
        int[] dp = new int[n];// 和subarray sum意义一样
        Deque<Integer> deque = new LinkedList<>();
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i > k && deque.peekFirst() == i - k - 1) {
                deque.pollFirst();
            }
            dp[i] = (deque.isEmpty() ? 0 : Math.max(dp[deque.peekFirst()], 0))
                + nums[i];
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
