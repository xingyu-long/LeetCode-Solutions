/*
 * @Date: 12/21/2020 10:36:36
 * @LastEditTime: 12/21/2020 10:37:58
 * @Description: Math
 */
package com.leetcode.math;

public class _908_SmallestRangeI {
    public int smallestRangeI(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int min = A[0], max = A[0];
        // max - k - (min + k);
        for (int a : A) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        // 注意需要和0比较
        return Math.max(0, (max - K) - (min + K));
    }
}

