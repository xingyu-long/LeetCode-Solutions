package com.leetcode.string.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/10/20, 09/02/2020
 * @Description: Subarray, at Most
 **/

public class _992_SubarrayswithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        // sliding window; at Most K的思路
        if (A == null || A.length == 0) return 0;
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    public int atMostK(int[] A, int K) {
        int start = 0;
        int end = 0;
        int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        while (end < A.length) {
            if (count.getOrDefault(A[end], 0) == 0) K--;
            count.put(A[end], count.getOrDefault(A[end], 0) - 1);
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
