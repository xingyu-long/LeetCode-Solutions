package com.leetcode.dynamic_programming;

import java.util.Arrays;

/**
 * @Date: 04/29/2020
 * @Description: DP, Sliding Window
 **/
public class _1423_MaximumPointsYouCanObtainfromCards {
    // time:O(n^2) space:O(n^2)
    public int maxScore(int[] cardPoints, int k) {
        if (cardPoints == null || cardPoints.length == 0) {
            return 0;
        }
        int n = cardPoints.length;
        int[][] memo = new int[n + 1][n + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(cardPoints, 0, n - 1, memo, k);
    }

    public int dfs(int[] nums, int left, int right, int[][] memo, int k) {
        if (k == 0) {
            return 0;
        }
        if (left > right) {
            return 0;
        }
        if (memo[left][right] != -1) {
            return memo[left][right];
        }
        int res = Integer.MIN_VALUE;
        res = Math.max(res, dfs(nums, left + 1, right, memo, k - 1) + nums[left]); // 选左边
        res = Math.max(res, dfs(nums, left, right - 1, memo, k - 1) + nums[right]); // 选右边
        memo[left][right] = res;
        return res;
    }

    // 转换为去寻找n - k最小的连续和。注意n = k的情况以及 后面的相减过程
    // time:O(n) space:O(1)
    public int maxScore2(int[] cardPoints, int k) {
        if (cardPoints == null || cardPoints.length == 0) {
            return 0;
        }
        int n = cardPoints.length;
        int rest = n - k; // 求这么多个最小
        int sum = 0;
        int res = 0;
        int total = 0;
        for (int card : cardPoints) {
            total += card;
        }
        if (rest <= 0) {
            return total;
        }
        for (int i = 0; i < cardPoints.length; i++) {
            sum += cardPoints[i];
            if (i >= rest - 1) {
                res = Math.max(res, total - sum);
                // System.out.println("sum =" + sum);
                sum -= cardPoints[i - rest + 1];
            }
        }
        return res;
    }
}
