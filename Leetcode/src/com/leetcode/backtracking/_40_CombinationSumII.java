package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _40_CombinationSumII {

    /**
     *  40. Combination Sum II
     *  When: 2019/04/29
     *  Review1: 2019/7/15
     *
     *  solution:
     *  基本与前面一致，只需要排序以及去重工作
     * @param candidates
     * @param target
     * @return
     */
    //time:O(2^n) space:O(n)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 需要注意到排序的部分 以及去重
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        helper(res, new ArrayList<Integer>(), candidates, target, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i != start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}
