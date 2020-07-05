package com.leetcode.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2019/7/15, 2019/10/13, 06/08/2020
 * @Description: DP
 **/
public class _377_CombinationSumIV {

    // 这个和coin change2的区别 [1, 3] [3,1]这里算两种，coin change2只算一种。
    // Bottom-up What's the difference between Coin Change problem?
    // 这个只是形式类似， 但含义完全没有联系 理解还是有问题。表示在dp[i]在和为i的情况下总有多少种。 有点累计和的感觉
    // https://www.youtube.com/watch?v=niZlmOtG4jM
    // https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
    public static int combinationSum4_2(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    // 利用数组的 dfs + memo。其实这个和上面的那个backtracking一样，只是计数的方式不一样，一个是返回base case。
    // 通过这个发现dp的关系，就是累加前面的结果（target大于num[i]的时候）
    // DFS + memo Top-down
    // time: sum{target/num_i} -> worse: target * # of |nums|
    private int[] dp;

    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return helper(nums, target);
    }

    public int helper(int[] nums, int target) {
        // goal;
        if (target == 0) {
            return 1;
        }
        if (dp[target] != -1) {
            return dp[target];
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }

        dp[target] = res;
        return res;
    }

    // 这个也比较经典
    public static List<String> generateAllFromBack(int[] nums, int target,
        HashMap<Integer, List<String>> map) {
        if (map.get(target) != null) {
            return map.get(target);
        }
        List<String> res = new ArrayList<>();
        if (target == 0) {
            res.add("");
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] >= 0) {
                // do not pass i + 1 to the next level because we have to reuse the number.
                List<String> list = generateAllFromBack(nums, target - nums[i], map);
                for (String temp : list) {
                    res.add(nums[i] + (temp.equals("") ? "" : ",") + temp);
                }
            }
        }
        map.put(target, res);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        HashMap<Integer, List<String>> map = new HashMap<>();
        List<String> res = generateAllFromBack(nums, target, map);
        for (String str : res) {
            System.out.println(str);
        }
    }
}