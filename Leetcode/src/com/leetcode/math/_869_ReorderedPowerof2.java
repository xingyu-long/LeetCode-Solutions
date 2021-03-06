/*
 * @Date: 03/21/2021 10:14:04
 * @LastEditTime: 03/21/2021 10:15:31
 * @Description: Math
 */
package com.leetcode.math;

public class _869_ReorderedPowerof2 {
    // 首先是把当前的N映射到以10的各种次方的和里面去
    // 之后尝试可能的是2的次方的数(0~30) 然后看构造与原来的相同
    public boolean reorderedPowerOf2(int N) {
        long sum = helper(N);
        for (int i = 0; i < 31; i++) {
            // 尝试是2的指数的情况
            if (helper(1 << i) == sum)
                return true;
        }
        return false;
    }

    private long helper(int N) {
        // 将数字的每位数都映射到10^x里面去，并且相加
        long res = 0;
        while (N > 0) {
            res += Math.pow(10, N % 10);
            N /= 10;
        }
        return res;
    }
}
