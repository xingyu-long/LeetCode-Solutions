/*
 * @Date: 12/28/2020 11:04:07
 * @LastEditTime: 12/28/2020 19:05:44
 * @Description: Math
 */
package com.leetcode.math;

public class _754_ReachANumber {
    // 不是特别懂
    // https://leetcode.com/problems/reach-a-number/discuss/112968/Short-JAVA-Solution-with-Explanation
    public int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0, sum = 0;
        while (sum < target) {
            step++;
            sum += step;
        }
        while ((sum - target) % 2 != 0) {
            step++;
            sum += step;
        }
        return step;
    }
}
