/*
 * @Date: 02/15/2020 13:22:23
 * @LastEditTime: 12/12/2020 20:41:45
 * @Description: Math
 */
package com.leetcode.math;

public class _453_MinimumMovestoEqualArrayElements {

    public int minMoves(int[] nums) {
        // 如何确定最终的状态？
        // 如何判断步数？
        // 倒着来思考:相当于每次只需要选择最大的数减去1
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        int res = 0;
        for (int num : nums) {
            res += num - min;
        }
        return res;
    }
}
