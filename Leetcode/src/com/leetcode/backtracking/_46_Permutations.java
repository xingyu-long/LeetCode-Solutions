package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _46_Permutations {

    /**
     *  46. Permutations
     *  When: 2019/04/30
     *  Review1: 2019/7/16
     *
     *  solution:
     *  基本思路与前面一致 但是没有start这个参数，因为这次是全部在数组里面获得并且 i 只能从 0开始 这样才能获取全部
     *
     * space: O(n)
     * time: O(n * n!)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(res, new ArrayList<Integer>(), nums);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 这里一直从0开始，因为这里需要能够遍历完里面的元素才行，以前的方法就不行
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue;  // O(n)
            list.add(nums[i]);
            helper(res, list, nums);
            list.remove(list.size() - 1);
        }
    }
}
