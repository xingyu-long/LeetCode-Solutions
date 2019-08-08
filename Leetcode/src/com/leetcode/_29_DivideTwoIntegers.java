package com.leetcode;

public class _29_DivideTwoIntegers {

    /**
     * 29. Divide Two Integers
     * When: 2019/04/04
     * review1:2019/8/8
     *
     * Test case:
     * dividend = 10, divisor = 3 -> res = 3
     * sign = 1
     *      sum = 3, multiple = 1; 执行while sum = 3 + 3 = 6 multiple = 2; return 2 + divide(10 - 6 = 4, 3)
     *                                                                                sum = 3, multiple = 1; return 1+ divide(4 - 3 = 1, 3)
     *                                                                                                                 1 < 3 return 0;
     *     return 0 + 1 + 2 = 3
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        // 考虑三种极端情况 1. + - 号 2. 越界问题 3. 被除数为0或者被除数小于除数
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        if (ldividend < ldivisor || ldividend == 0) return 0;
        long lres = divide(ldividend, ldivisor);
        int res;
        if (lres > Integer.MAX_VALUE) {
            res = (sign == 1) ? Integer.MAX_VALUE: Integer.MIN_VALUE;
        } else {
            res = (int)lres * sign;
        }
        return res;
    }

    public long divide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) < ldividend) {
            sum += sum;
            multiple += multiple;
        }
        // 用这里的倍数作为答案返回！并且使用递归，这里还有上面的 <= 或者 < 都不影响的问题（但是过程不太一样）
        return multiple + divide(ldividend - sum, ldivisor);
    }
}
