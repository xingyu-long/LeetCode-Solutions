package com.leetcode.math;

public class _342_PowerofFour {
    /**
     * 342. Power of Four
     * When:2019/8/10
     * Difficulty: Easy
     *
     * @param num
     * @return
     */
    // time:O(n) space:O(1)
    public boolean isPowerOfFour(int num) {
        if (num > 1) {
            while (num % 4 == 0) {
                num /= 4;
            }
        }
        return num == 1;
    }

    // time:O(1) 跟之前的log3处理是一样
    public boolean isPowerOfFour2(int num) {
        return (Math.log(num) / Math.log(4)) % 1 == 0;
    }
}
