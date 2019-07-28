package com.leetcode.dynamicProgramming;

public class _213_HouseRobberII {
    /**
     *  213. House Robber II
     *  When:2019/7/28
     *  Difficulty: Medium
     * @param nums
     * @return
     */
    // 如何表示环状的约束？把circle 拆开成两个row
    //https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(helper(nums, 0, nums.length - 2), helper(nums, 1, nums.length - 1));
    }
        // 用之前的prevNo, prevYes来计算
    public int helper(int[] nums, int lo, int hi) {
        int prevNo = 0;
        int prevYes = 0;
        for (int i = lo; i <= hi; i++) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = temp + nums[i];
        }
        return Math.max(prevNo, prevYes);
    }
}
