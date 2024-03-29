package com.leetcode.dynamic_programming;

public class _845_Longest_Mountain_in_Array {
    // 其实就是利用两个数组前后记录到当前位置的情况。
    public int longestMountain(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] inc = new int[n];
        int[] dec = new int[n];

        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                inc[i] = inc[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                dec[i] = dec[i + 1] + 1;
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (inc[i] > 0 && dec[i] > 0)
                res = Math.max(res, inc[i] + dec[i] + 1);
        }

        return res;
    }
}