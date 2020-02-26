package com.leetcode.array.counter;

import java.util.Deque;
import java.util.LinkedList;

/**
 * When:02/24/2020
 */
public class _862_ShortestSubarraywithSumatLeastK {
    // time:O(n) space:O(n)
    // 利用累计和来做然后看prefix[i] - prefix[d[0]]比较看是否符合
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int res = Integer.MAX_VALUE;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }
        Deque<Integer> deque = new LinkedList<>(); // 用来存储可能开始的index
        for (int i = 0; i <= n; i++) {
            while (deque.size() > 0 && prefix[i] - prefix[deque.peekFirst()] >= K)
                res = Math.min(res, i - deque.pollFirst());
            while (deque.size() > 0 && prefix[i] <= prefix[deque.peekLast()])
                deque.pollLast();
            deque.addLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
