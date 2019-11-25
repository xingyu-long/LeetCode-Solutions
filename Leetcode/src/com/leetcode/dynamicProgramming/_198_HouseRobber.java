package com.leetcode.dynamicProgramming;

public class _198_HouseRobber {


    /**
     * 198. House Robber
     * When:2019/5/8
     * Review1:2019/7/28
     * Difficulty: Easy
     * <p>
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed, the only constraint stopping you from robbing
     * each of them is that adjacent houses have security system connected and it will automatically contact
     * the police if two adjacent houses were broken into on the same night.
     * <p>
     * <p>
     * [1, 3, 2, 4, 1]
     * No  Yes
     * 1 :  0    1
     * 3 :  1    3
     * 2 :  3    3
     *
     * @param nums
     * @return
     */

    // time: O(n) space: O(1)
    // https://www.youtube.com/watch?v=-i2BFAU25Zk
    public int rob(int[] nums) {
        int notRob = 0; // 表示当前这个值不偷，保留前面最大的情况
        int rob = 0; // 表示当前这个值偷，并且需要加上前面最大的。

        for (int num : nums) {
            int temp = Math.max(notRob, rob);
            rob = notRob + num;
            notRob = temp;
        }
        return Math.max(rob, notRob);
    }

    // space:O(n)
    // 表示为改点偷与不偷的问题
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; //第一个房间（只有一个的时候，有肯定比没有的好）
        dp[1] = Math.max(nums[0], nums[1]);//第二个房间，不管怎么选，就只能是其中较大的那个
        // 后面则就是选择与不选择的情况然后 math.max 比较 相当于是当前选择了的话 i - 1不能用 所以当前值 + i - 2的最好情况
        // 不选择 那么就是看 i-1 最好的情况
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
