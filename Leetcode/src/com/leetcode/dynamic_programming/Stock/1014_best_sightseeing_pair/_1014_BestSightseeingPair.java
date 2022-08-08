package com.leetcode.dynamic_programming.Stock;

public class _1014_BestSightseeingPair {
    // 类似于best and sell stock I
    public int maxScoreSightseeingPair(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        // imax表示 A[i] + i那一块
        // 然后我们要找最大的情况，之后尝试A[j] - j构成最大值试试。
        int res = 0;
        int imax = 0;
        for (int i = 0; i < A.length; i++) {
            res = Math.max(res, imax + A[i] - i);
            imax = Math.max(imax, A[i] + i);
        }
        return res;
    }
}
