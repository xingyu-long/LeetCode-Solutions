package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _377_CombinationSumIV {

    /**
     * 377. Combination Sum IV
     * time: 2019/7/15
     * solution:
     * DP
     * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     * 如果是原来的方法，那么i应该从0而不是index开始，因为那样可以遍历全部可能
     *
     * @param nums
     * @param target
     * @return
     */

    // 如果是原来的方法，那么i应该从0而不是index开始，因为那样可以遍历全部可能
    // 但是会 Time Limit Exceeded!!!!
    public static int combinationSum4(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, target, 0);
        for (List<Integer> resOfEach : res) {
            System.out.println(resOfEach.toString());
        }
        return res.size();
    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int target, int index) {

        // our goal
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //这里需要剪枝 即 constraints
        if (target < 0) {
            return;
        }
        // choice
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, target - nums[i], i);
            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        combinationSum4(nums, target);
    }

    // still will be TLE
    public int _2combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += _2combinationSum4(nums, target - nums[i]);
            }
        }
        return res;
    }

    // DP Top-down
    // 不懂bottom-up
    private int[] dp;

    public int _3combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }
    public int helper(int[] nums, int target) {
        // goal;
        if (dp[target] != -1) {
            return dp[target];
        }

        int res = 0;
        // choice
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }
}
