/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 02/27/2021 10:30:57
 * @Description: Math
 */
package com.leetcode.math;

public class _29_DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        // 考虑三种极端情况 1. + - 号 2. 越界问题 3. 被除数为0或者被除数小于除数
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        if (ldividend < ldivisor || ldividend == 0) {
            return 0;
        }
        long lres = divide(ldividend, ldivisor);
        if ((sign == -1) && lres * sign < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if ((sign == 1) && lres * sign > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return sign * (int) lres;
    }

    public long divide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) {
            return 0;
        }
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
