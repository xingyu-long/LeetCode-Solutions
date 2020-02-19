package com.leetcode.string.slidingWindow;

/**
 * _487_MaxConsecutiveOnesII
 */
public class _487_MaxConsecutiveOnesII {

    public int longestOnes(int[] A) {
        if (A == null || A.length == 0) return 0;
        int res = 0;
        int zeroCount = 0;
        int start = 0;
        int K = 1;
        for (int end = 0; end < A.length; end++) {
            if (A[end] == 0) {
                zeroCount++;
            }
            while (zeroCount > K) {
                if (A[start] == 0) {
                    zeroCount--;
                }
                start++;//移动窗口
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}