package com.leetcode.dynamicProgramming;

import java.util.HashMap;

public class _494_TargetSum {

    // dfs + memo
    public int findTargetSumWays2(int[] nums, int S) {
        // backtracking的问题  index -> n && == S return 1;
        // '-', 以及 '+',
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();
        return dfs(nums, 0, S, map);
    }

    // 不用保持其prev，因为这里都是直接+ -
    // 2^n 没有cache的话 不知道怎么来cache。。。。
    // 不用二维数组，是因为会有负数的原因。
    // time:O(sum * n) space:O(1)
    public int dfs(int[] nums, int index, int target, HashMap<String, Integer> map) {
        String key = index + "*" + target;
        if (index == nums.length) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        if (map.get(key) != null) {
            return map.get(key);
        }

        int res = 0;
        res += dfs(nums, index + 1, target - nums[index], map);
        res += dfs(nums, index + 1, target + nums[index], map);
        map.put(key, res);
        return res;
    }
}
