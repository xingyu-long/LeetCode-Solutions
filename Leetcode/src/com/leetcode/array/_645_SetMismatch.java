/*
 * @Date: 07/26/2020 16:20:56
 * @LastEditTime: 03/02/2021 10:02:57
 * @Description: Array, mark.
 */
package com.leetcode.array;

public class _645_SetMismatch {
    // 利用类似的方法先标记目前已经遍历过的。
    // 不要用那个bucket sort，因为有情况不work: 3,2,3,4,6,5
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0)
                res[0] = index + 1;
            else
                nums[index] = -nums[index];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }
}
