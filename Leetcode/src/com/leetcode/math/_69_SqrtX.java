package com.leetcode.math;

public class _69_SqrtX {

    /**
     *  69. Sqrt(x)
     *  When: 2019/04/04
     *  When:2019/7/20
     *
     * solution:
     * 使用二分法
     * 这里还有个牛顿法 No.367
     * @param x
     * @return
     */
    // 记住考虑其中overflow的情况
    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (mid * mid >= x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (right * right <= x) return (int) right;
        else return (int) left;
    }
}
