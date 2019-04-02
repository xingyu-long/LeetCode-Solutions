package com.leetcode;

public class _7_ReverseInteger {

    /**
     * 7. Reverse Integer
     * When: 2019/04/02
     *
     *
     * integer range: [−2^31,  2^31 − 1]
     * @param x
     * @return
     */
    public int reverse(int x) {
        // 记住这种解法
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10; // 得到最后一位
            x /= 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        }
        return (int)res;
    }
}
