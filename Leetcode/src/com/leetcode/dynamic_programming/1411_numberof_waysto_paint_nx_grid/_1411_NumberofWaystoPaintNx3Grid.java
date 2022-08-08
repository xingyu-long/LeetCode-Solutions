package com.leetcode.dynamic_programming;

/**
 * @Date: 04/12/2020
 * @Description: Dynamic Programming, Math
 **/
public class _1411_NumberofWaystoPaintNx3Grid {
    // time:O(n) space: O(n) 可以优化到O(1)
    // https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574943/Java-Detailed-Explanation-with-Graph-Demo-DP-Easy-Understand
    public int numOfWays(int n) {
        // 3 * 2 * 2;
        int MOD = (int) Math.pow(10, 9) + 7;
        long[] threeColors = new long[n]; // 三种颜色全用 3 * 2 * 1
        long[] twoColors = new long[n]; // 只用两种验证 C^{2}_{3} * 2
        threeColors[0] = 6;
        twoColors[0] = 6;
        for (int i = 1; i < n; i++) {
            threeColors[i] = (threeColors[i - 1] * 2 + twoColors[i - 1] * 2) % MOD;
            twoColors[i] = (threeColors[i - 1] * 2 + twoColors[i - 1] * 3) % MOD;
        }
        return (int) (threeColors[n - 1] + twoColors[n - 1]) % MOD;
    }
}
