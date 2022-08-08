package com.leetcode.stack_priority_queue.monoQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date: 02/24/2020, 03/10/2020, 04/12/2020
 * @Description: Sliding Window, Deque
 **/
public class _862_ShortestSubarraywithSumatLeastK {

    // time:O(n) space:O(n)
    // 利用累计和来做然后看prefix[i] - prefix[d[0]]比较看是否符合
    // 保持单调递增的情况
    // https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int res = Integer.MAX_VALUE;
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }
        Deque<Integer> deque = new LinkedList<>(); // 用来存储可能开始的index
        for (int i = 0; i <= n; i++) {
            while (deque.size() > 0 && prefix[i] - prefix[deque.peekFirst()] >= K) {
                res = Math.min(res, i - deque.pollFirst()); // 符合情况的时候
            }
            while (deque.size() > 0 && prefix[i] <= prefix[deque.peekLast()]) {
                deque.pollLast(); // 表示当前deque里面的最后一个元素不会是candidate
            }
            deque.addLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
