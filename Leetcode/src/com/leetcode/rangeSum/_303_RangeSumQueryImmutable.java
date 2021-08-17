/*
 * @Date: 07/06/2019 04:20:11
 * @LastEditTime: 08/16/2021 21:38:18
 * @Description: 1d range sum
 */
package com.leetcode.rangeSum;

public class _303_RangeSumQueryImmutable {

    private int[] sum;

    //前j个的和减去前i个的和
    public _303_RangeSumQueryImmutable(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

}
