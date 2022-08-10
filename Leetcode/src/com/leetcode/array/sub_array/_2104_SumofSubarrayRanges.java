/*
 * @Date: 07/24/2022 12:10:00
 * @LastEditTime: 07/24/2022 12:10:05
 * @Description: Sub array
 */
package com.leetcode.array.sub_array;

public class _2104_SumofSubarrayRanges {
    // time: O(n^2)
    // space: O(1)
    public long subArrayRanges(int[] nums) {
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i], max = nums[i];
            for (int j = 0; i + j < nums.length; j++) {
                min = Math.min(min, nums[i + j]);
                max = Math.max(max, nums[i + j]);
                
                res += (max - min);
            }
        }
        return res;
    }

    // O(n) solution ?
}
