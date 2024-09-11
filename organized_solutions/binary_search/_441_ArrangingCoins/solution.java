package com.leetcode.binary_search;

/**
 * @Date: 07/01/2020
 * @Description: binary search
 **/
public class _441_ArrangingCoins {

    // time:O(logN)
    public int arrangeCoins(int n) {
        long left = 1;
        long right = n;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            long sum = cal(mid);
            if (sum >= n) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (cal(right) <= n) {
            return (int) right;
        }
        return (int) left;
    }

    private long cal(long j) {
        return (1 + j) * (j) / 2;
    }
}
