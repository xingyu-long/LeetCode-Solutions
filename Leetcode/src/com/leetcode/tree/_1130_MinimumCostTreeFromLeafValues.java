package com.leetcode.tree;

public class _1130_MinimumCostTreeFromLeafValues {
    public static int minCost(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[][] memo = new int[n][n];// 这里的含义是i-j里面最小的值。最后返回从开始点到结束点的memo[start][end];
        return dfs(nums, 0, n - 1, memo);
    }

    public static int dfs(int[] nums, int start, int end, int[][] memo) {
        if (start == end) return 0; // 只有一个元素的时候，是不考虑的，因为没有办法划分
        // 注意各种临界值，注意i
        if (memo[start][end] > 0) return memo[start][end];
        int res = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int left = dfs(nums, start, i, memo);
            int right = dfs(nums, i + 1, end, memo);
            int maxLeft = 0, maxRight = 0;
            for (int j = start; j <= i; j++) maxLeft = Math.max(maxLeft, nums[j]);
            for (int j = i + 1; j <= end; j++) maxRight = Math.max(maxRight, nums[j]);
            res = Math.min(res, left + right + maxLeft * maxRight);
        }
        memo[start][end] = res;
        return res;
    }
    public static void main(String[] args) {
        System.out.println(minCost(new int[]{6, 2, 4}));
    }
}
