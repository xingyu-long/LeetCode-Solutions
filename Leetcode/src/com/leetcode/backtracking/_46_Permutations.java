package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _46_Permutations {

    /**
     * 46. Permutations
     * When: 2019/04/30
     * Review1: 2019/7/16
     * review2:2019/10/13
     * <p>
     * solution:
     * 基本思路与前面一致 但是没有start这个参数，因为这次是全部在数组里面获得并且 i 只能从 0开始 这样才能获取全部
     * <p>
     * space: O(n)
     * time: O(n!)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
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
