package com.leetcode.math;
    // When: 02/23/2020
public class _1362_ClosestDivisors {
    // time: O(sqrt(num)), space:O(1)
    // 当时做的是通过列举所有的，然后根据abs diff的差值来决定
    // 其实不用只需要从大的开始 sqrt(x + 1 / x + 2);
    public int[] closestDivisors(int num) {
        // if (num == 1) return new int[]{1,2};
        for (int i = (int) Math.sqrt(num + 2); i > 0;  i--) {
            if ((num + 1) % i == 0) {
                return new int[]{i, (num + 1) / i};
            }

            if ((num + 2) % i == 0) {
                return new int[]{i, (num + 2) / i};
            }
        }
        return new int[]{};
    }
}