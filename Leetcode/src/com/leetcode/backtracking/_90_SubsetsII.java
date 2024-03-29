package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/04/26, 2019/7/16, 2019/10/7, 06/08/2020
 * @Description: Backtracking
 **/
public class _90_SubsetsII {

    // sort + 去重（同一级别的去重 i != index && nums[i] == nums[i - 1]）
    //time: O(2^n) space:O(n)
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 一定要排序，这样利于去重
        Arrays.sort(nums);

        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public static void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            // 可以利用这个来去重，防止同一层的重复
            // if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            dfs(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // BFS
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> sub = new ArrayList<>(res.get(i));
                sub.add(num);
                if (!res.contains(sub)) {
                    res.add(new ArrayList<>(sub));
                }
            }
        }
        return res;
    }
}

