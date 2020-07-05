package com.leetcode.array;

/**
 * @Date: 05/24/2020
 * @Description: Monotonic
 **/
public class _896_MonotonicArray {
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }
        int sign = -2; // -2: init, -1: decrease, 0: equal, 1: increase
        int n = A.length;
        for (int i = 0; i < n; i++) {
            if (i == 0) continue;
            if (sign == -2) {
                if (A[i] > A[i - 1]) {
                    sign = 1;
                } else if (A[i] < A[i - 1]) {
                    sign = -1;
                } else {
                    sign = 0;
                }
                // continue;
            }
            if (sign == 0) {
                sign = -2; // 重新找情况
                continue;
            }
            if (sign == 1 && A[i] < A[i - 1]) return false;
            if (sign == -1 && A[i] > A[i - 1]) return false;
        }
        return true;
    }

    public boolean isMonotonic2(int[] A) {
        boolean inc = true, dec = true;
        for (int i = 1; i < A.length; i++) {
            inc &= (A[i] >= A[i - 1]);
            dec &= (A[i] <= A[i - 1]);
        }
        return inc || dec;
    }
}
