package com.leetcode.binary_search;

public class _1011_CapacityToShipPackagesWithinDDays {
    // binary search like the monkey eats banana.
    public int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length == 0) return 0;
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (needDays(weights, mid) > D) left = mid;
            else right = mid;
        }
        if (needDays(weights, left) <= D) return left;
        else return right;
    }

    public int needDays(int[] weights, int speed) {
        int cur = 0;
        int count = 1;
        for (int w : weights) {
            if (cur + w > speed) {
                count += 1;
                cur = 0;
            }
            cur += w;
        }
        return count;
    }
}
