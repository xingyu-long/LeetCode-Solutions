package com.leetcode.string.slidingWindow;

/**
 * @Date: 2019/7/21 06/02/2020
 * @Description: Sliding Window
 **/
public class _1004_MaxConsecutiveOnesIII {
    // time:O(n) space:O(1)
    public int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0, end = 0, zeroCount = 0;
        int res = 0, n = A.length;
        while (end < n) {
            if (A[end] == 0) {
                zeroCount++;
            }
            while (zeroCount > K) {
                if (A[start] == 0) zeroCount--;
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}
