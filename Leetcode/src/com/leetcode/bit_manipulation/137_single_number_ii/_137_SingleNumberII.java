/*
 * @Date: 08/11/2020 15:07:14
 * @LastEditTime: 11/06/2021 19:25:04
 * @Description: Bit
 */
package com.leetcode.bit_manipulation;

public class _137_SingleNumberII {

    // 主要是看每一位的情况
    // time:O(n) space:O(1)
    public int singleNumber(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 计算每一位上的1's的个数
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((nums[j] >> i) & 1) == 1) {
                    sum++;
                }
            }
            sum %= 3;
            if (sum != 0) {
                res |= (sum << i);
            }
        }
        return res;
    }
}
