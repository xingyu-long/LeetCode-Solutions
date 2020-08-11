package com.leetcode.rangeSum;

public class _303_RangeSumQueryImmutable {

    /**
     * 303. Range Sum Query - Immutable
     * When: 2019/7/6

     * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

     Example:
     Given nums = [-2, 0, 3, -5, 2, -1]

     sumRange(0, 2) -> 1
     sumRange(2, 5) -> -1
     sumRange(0, 5) -> -3
     Note:
     You may assume that the array does not change.
     There are many calls to sumRange function.

     [-2, 0, 3, -5, 2, -1]

     time : O(n)
     space : O(n)
     */

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
