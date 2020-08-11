package com.leetcode.dynamicProgramming.backCheckDP;

import java.util.Arrays;

public class _673_NumberofLongestIncreasingSubsequence {
    // time:O(n^2) space:O(n)
    public int findNumberOfLIS(int[] nums) {
        // 二维来做吗
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n]; // 表示以当前i结尾的最大增序长度
        int[] ways = new int[n];// 表示以当前i结尾，有多少条路径可以走到这里。
        Arrays.fill(dp, 1);
        Arrays.fill(ways, 1);
        ways[0] = 1;
        int max = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] == dp[j] + 1)  { // 表示有相同的可以走到
                        ways[i] += ways[j];
                    } else if (dp[i] < dp[j] + 1) { // 更新，然后走的同一条路，所以ways不用加，直接赋值
                        dp[i] = dp[j] + 1;
                        ways[i] = ways[j];
                    }
                }
            }

            // 这个是最经典的，更新结果
            if (max == dp[i]) res += ways[i];
            else if (max < dp[i]) {
                max = dp[i];
                res = ways[i];
            }
        }
        return res;
    }
}
