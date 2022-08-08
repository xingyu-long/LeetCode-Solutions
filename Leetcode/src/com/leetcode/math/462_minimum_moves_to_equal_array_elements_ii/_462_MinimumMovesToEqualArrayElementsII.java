/*
 * @Date: 12/12/2020 20:48:15
 * @LastEditTime: 12/12/2020 20:48:50
 * @Description: Sort, two pointer, Math
 */
package com.leetcode.math;

import java.util.Arrays;

public class _462_MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        // 类似于best meeting point
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int res = 0;
        // 每次找中间的那个数，则用收尾做差找到值
        while (i < j) {
            res += nums[j] - nums[i];
            i++;
            j--;
        }
        return res;
    }
}