package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _377_CombinationSumIV {

    /**
     * 377. Combination Sum IV
     * time: 2019/7/15
     * review1:2019/10/13
     * solution:
     * DP
     * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     * 如果是原来的方法，那么i应该从0而不是index开始，因为那样可以遍历全部可能
     *
     * @param nums
     * @param target
     * @return
     */
    // DFS + memo Top-down
    // time: sum{target/num_i} -> worse: target * # of |nums|
    public int combinationSum4_1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        return dfs(nums, target, map);
    }

    public int dfs(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (map.containsKey(target)) return map.get(target);
        if (target == 0) return 1;
        int val = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                val += dfs(nums, target - nums[i], map);
            }
        }
        map.put(target, val);
        return val;
    }

    // Bottom-up What's the difference between Coin Change problem?
    // 这个只是形式类似， 但含义完全没有联系 理解还是有问题。表示在dp[i]在和为i的情况下总有多少种。 有点累计和的感觉
    // https://www.youtube.com/watch?v=niZlmOtG4jM
    public static int combinationSum4_2(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        System.out.println(combinationSum4_2(nums, target));
    }
}