package com.leetcode.dynamicProgramming;

public class _198_HouseRobber {


    /**
     * 198. House Robber
     *      You are a professional robber planning to rob houses along a street.
     *      Each house has a certain amount of money stashed, the only constraint stopping you from robbing
     *      each of them is that adjacent houses have security system connected and it will automatically contact
     *      the police if two adjacent houses were broken into on the same night.
     *
     *      [1, 3, 2, 4, 1]
     *           No  Yes
     *      1 :  0    1
     *      3 :  1    3
     *      2 :  3    3
     *
     * time: 2019/05/08
     *
     * @param nums
     * @return
     */

    // time: O(n) space: O(1)
    public int rob(int[] nums) {
        int prevNo = 0; // 表示当前这个值不偷，保留前面最大的情况
        int prevYes = 0; // 表示当前这个值偷，并且需要加上前面最大的。

        for (int num : nums) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = temp + num;
        }

        return Math.max(prevNo, prevYes);
    }
}
