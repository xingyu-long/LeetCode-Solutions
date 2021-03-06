/*
 * @Date: 01/15/2021 10:15:32
 * @LastEditTime: 01/15/2021 10:16:25
 * @Description: Simulation
 */
package com.leetcode.array;

public class _1646_GetMaximumInGeneratedArray {
    public int getMaximumGenerated(int n) {
        // 模拟题
        if (n <= 0) {
            return 0;
        }

        int[] nums = new int[n + 1];
        nums[1] = 1;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                int j = (i - 1) / 2;
                nums[i] = nums[j] + nums[j + 1];
            }
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}
