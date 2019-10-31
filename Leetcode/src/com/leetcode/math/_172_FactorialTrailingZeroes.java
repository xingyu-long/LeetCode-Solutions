package com.leetcode.math;

public class _172_FactorialTrailingZeroes {
    /**
     * 172. Factorial Trailing Zeroes
     * When:2019/8/12
     * review1:10/30/2019
     * Difficulty: Easy
     * @param n
     * @return
     */
    // 产生0的地方就是 2 * 5 以及这个的倍数。
    public int trailingZeroes(int n) {
        // 利用2的个数来判断0
        int res = 0;
        while (n != 0) {
            res += n / 5;
            n /= 5;
        }
        return res;
    }
}
