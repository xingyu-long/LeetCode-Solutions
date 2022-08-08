package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date:  2019/04/30, 2019/7/16, 2019/10/13, 06/08/2020
 * @Description: Backtracking
 **/
public class _46_Permutations {
    // visited数组即可
    // time: O(n!) space: O(n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    // 每一次都可以循环全部，然后用visited作为标记！
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                dfs(res, list, nums, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
