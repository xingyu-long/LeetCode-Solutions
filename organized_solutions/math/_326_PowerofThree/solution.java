/*
 * @Date: 07/21/2019 04:03:09
 * @LastEditTime: 04/27/2021 10:08:18
 * @Description: Math
 */
package com.leetcode.math;

public class _326_PowerofThree {

    // time:O(n) space:O(1)
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // 利用对数
    // time :O(1)
    public boolean isPowerOfThree2(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
}