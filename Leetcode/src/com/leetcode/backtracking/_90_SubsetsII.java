package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _90_SubsetsII {

    /**
     *  90. Subsets II
     *  When: 2019/04/26
     *  Review1: 2019/7/15
     *
     * solution:
     * 也是用回溯法，只要的区别是去重的情况
     *
     * 这里记得注意当i=2的时候 会有continue的情况
     * 比较表示是 index = 0 与 i = 0, 1, 2的情况
     * @param nums
     * @return
     */
    //time: O(2^n) space:O(n)
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // 一定要排序，这样利于去重
        Arrays.sort(nums);

        helper(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

