package com.leetcode.array;

public class _334_IncreasingTripletSubsequence {

    /**
     *  334. Increasing Triplet Subsequence
     *  When:2019/8/2
     *
     * @param nums
     * @return
     */
    //  利用dp time:O(n^2) space:O(n)
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        // 利用和300 题一样的思路 dp[i]表示在i之前有多少个比它小（加上它自己）
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }
        return res >= 3;
    }

    //  相当于分别找出最小的那个数，中间那个数，第三小的数然后返回true
    // time:O(n) space:O(1)
    public boolean increasingTriplet2(int[] nums) {
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) min = num;
            else if (num < secondMin) secondMin = num;
            else if (num > secondMin) return true;
        }
        return false;
    }
}