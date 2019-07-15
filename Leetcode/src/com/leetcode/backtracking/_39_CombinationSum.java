package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _39_CombinationSum {

    /**
     *  39. Combination Sum
     *  When: 2019/04/29
     *  Review1: 2019/7/15
     * solution：基本跟前面的回溯法思路一致
     *
     * time: ???
     * space: ???
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 基本也是用回溯
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
