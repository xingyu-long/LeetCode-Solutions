package com.leetcode.array;

/**
 * @Date: 05/24/2020, 09/03/2020
 * @Description: Monotonic
 **/
public class _896_MonotonicArray {
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length == 0) {
            return true;
        }

        // two pass
        // set as increase;
        int i = 1;
        for (; i < A.length; i++) {
            if (A[i] < A[i - 1]) break;
        }
        if (i == A.length) return true;
        i = 1;
        // set as decrease;
        for (; i < A.length; i++) {
            if (A[i] > A[i - 1]) break;
        }

        if (i == A.length) return true;

        return false;
    }

    public boolean isMonotonic2(int[] A) {
        boolean increase = true, decrease = true;
        for (int i = 1; i < A.length; i++) {
            increase &= (A[i] >= A[i - 1]);
            decrease &= (A[i] <= A[i - 1]);
        }
        return increase || decrease;
    }
}
