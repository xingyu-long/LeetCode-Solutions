package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _491_Increasing_Subsequences {
    // 类似于subset，主要是去重的问题：相同层的相同数字需要去掉。
    public List<List<Integer>> findSubsequences(int[] nums) {
        // backtracking
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, null, 0);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, Integer prev, int index) {
        if (list.size() > 1) {
            res.add(new ArrayList<>(list));
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (prev != null && nums[i] < prev) continue;
            if (visited.contains(nums[i])) continue;
            visited.add(nums[i]);
            list.add(nums[i]);
            dfs(res, list, nums, nums[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}
