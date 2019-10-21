package com.leetcode.string.slidingWindow;

import java.util.HashMap;

public class _992_SubarrayswithKDifferentIntegers {
    /**
     * 992. Subarrays with K Different Integers
     * time:2019/10/20
     * Difficulty:Hard
     * solution: 利用atMost的思路。
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        // sliding window; at Most K的思路
        if (A == null || A.length == 0) return 0;
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    public int atMostK(int[] A, int K) {
        int start = 0;
        int end = 0;
        int res = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        while (end < A.length) {
            if (count.getOrDefault(A[end], 0) == 0) K--;
            count.put(A[end], count.getOrDefault(A[end], 0) - 1); // 以前是--现在是加这个个数。
            while (K < 0) {
                count.put(A[start], count.get(A[start]) + 1);
                if (count.get(A[start]) == 0) K++;
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }
}
