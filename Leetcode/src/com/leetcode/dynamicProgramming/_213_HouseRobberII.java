package com.leetcode.dynamicProgramming;

/**
 * @Date: 07/18/2020
 * @Description: 时间顺序DP
 **/
public class _213_HouseRobberII {

    // 如何表示环状的约束？把circle 拆开成两个row
    //https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation
    // 分成两段去计算（包括开头和不包括的情况）
    // time:O(n) space:O(n)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 因为开头和结尾两个不能同时出现。
        int include = helper(nums, 0, n - 1);
        int notInclude = helper(nums, 1, n);
        return Math.max(include, notInclude);
    }

    public int helper(int[] nums, int left, int right) {
        int rob = 0;
        int notRob = 0;
        for (int i = left; i < right; i++) {
            int prevMax = Math.max(rob, notRob);
            rob = notRob + nums[i];
            notRob = prevMax;
        }
        return Math.max(rob, notRob);
    }
}
