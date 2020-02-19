package com.leetcode.dynamicProgramming;

public class _213_HouseRobberII {
    /**
     *  213. House Robber II
     *  When:2019/7/28
     *  review1: 11/14
     *  Difficulty: Medium
     * @param nums
     * @return
     */
    // 如何表示环状的约束？把circle 拆开成两个row
    //https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation
    // 分成两段去计算（包括开头和不包括的情况）
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        // 因为开头和结尾两个不能同时出现。
        int include = helper(nums, 0, n - 1); 
        int notInclude = helper(nums, 1, n);
        return Math.max(include, notInclude);
    }

    public int helper(int[] nums, int left, int right) {
        int rob = 0;
        int notRob = 0;
        for (int i = left; i < right; i++) {
            int temp = Math.max(rob, notRob);
            rob = notRob + nums[i];
            notRob = temp;
        }
        return Math.max(rob, notRob);
    }
}
