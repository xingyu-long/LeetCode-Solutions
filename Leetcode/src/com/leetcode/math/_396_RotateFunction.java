package com.leetcode.math;

/**
 * _396_RotateFunction
 */
public class _396_RotateFunction {

    // https://www.cnblogs.com/grandyang/p/5869791.html
    // 规律如下
    //  F(0) = 0A + 1B + 2C +3D

    // F(1) = 0D + 1A + 2B +3C

    // F(2) = 0C + 1D + 2A +3B

    // F(3) = 0B + 1C + 2D +3A

    // 那么，我们通过仔细观察，我们可以得出下面的规律：

    // sum = 1A + 1B + 1C + 1D

    // F(1) = F(0) + sum - 4D

    // F(2) = F(1) + sum - 4C

    // F(3) = F(2) + sum - 4B
    // 这样就可以把O(n^2) 降到 O(n)
    public int maxRotateFunction(int[] A) {
        int n = A.length;
        if (A == null || n == 0) return 0;
        int sum = 0;
        int candidate = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            candidate += i * A[i];
        }
        int max = candidate;
        for (int i = 1; i < n; i++) {
            candidate = candidate + sum - n * A[n - i];
            max = Math.max(max, candidate);
        }
        return max;
    }
}