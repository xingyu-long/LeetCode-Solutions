package com.leetcode;

public class _7_ReverseInteger {

    /**
     * 7. Reverse Integer
     * When: 2019/04/02
     *
     *
     * integer range: [−2^31,  2^31 − 1]
     * Test case:
     * x = 123:
     *      res = 0; res = 0 * 10 + x % 10 = 3（得到个位数的1） x / 10 = 12;
     *      res = 3; res = 3 * 10 + 12 % 10 = 32(得到十位数的2) x / 10 = 1;
     *      res = 32; res = 32 * 10 + 1 % 10 = 321 （得到百位数的1） x / 10 = 0;
     * solution:
     *  某一个数 / 10 表示获取前 n - 1位数
     *  某一个数 % 10 表示获取最后一位数
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
