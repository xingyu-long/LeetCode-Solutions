package com.leetcode.math;

public class _172_FactorialTrailingZeroes {
    /**
     * 172. Factorial Trailing Zeroes
     * When:2019/8/12
     * Difficulty: Easy
     * @param n
     * @return
     */
    // 产生0的地方就是 2 * 5 以及这个的倍数，并且是5的个数决定0的个数（因为2的个数太多了）
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
