package com.leetcode;

public class _53_MaximumSubarray {

    /**
     * 53. Maximum Subarray
     * when: 2019/03/26
     *
     * 涉及到的数据结构或者算法：
     * DP（动态规划）
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        /**solution1：暴力解法
        if (nums.length == 1) return nums[0];
        //里面只包含负数 那肯定最大的就是第一个数
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i; j < nums.length; j++){
                sum += nums[j];
                res = Math.max(res, sum);
            }
            sum = 0;
        }
        return res;

         **/
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0: dp[i - 1]); //这里表示如果前面有小于0的部分肯定越来越小
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
