package com.leetcode.math;

public class _326_PowerofThree {

    /**
     *  326. Power of Three
     *  When:2019/7/21
     * @param n
     * @return
     */
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