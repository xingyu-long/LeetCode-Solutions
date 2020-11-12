package com.leetcode.dynamicProgramming;

public class _978_LongestTurbulentSubarray {
    // 类似于wiggle sequence，但是需要注意不能有间隔的情况
    public int maxTurbulenceSize(int[] A) {
        // wiggle sequence?
        int pos = 1, neg = 1;
        int res = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                pos = neg + 1;
                neg = 1;
            } else if (A[i] < A[i - 1]) {
                neg = pos + 1;
                pos = 1;
            } else {
                pos = 1;
                neg = 1;
            }
            res = Math.max(res, Math.max(pos, neg));
        }
        return res;
    }
}
