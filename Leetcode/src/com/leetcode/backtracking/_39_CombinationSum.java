package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39_CombinationSum {

    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates == null || candidates.length == 0) {
                return new ArrayList<>();
            }
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates);
            dfs(res, new ArrayList<>(), 0, candidates, target);
            return res;
        }

        public void dfs(List<List<Integer>> res, List<Integer> list, int index, int[] candidates, int target) {
            if (target == 0) {
                res.add(new ArrayList<>(list));
            }

            for (int i = index; i < candidates.length; i++) {
                if (target - candidates[i] >= 0) {
                    list.add(candidates[i]);
                    dfs(res, list, i, candidates, target - candidates[i]);
                    list.remove(list.size() - 1);
                } else {
                    break;
                }
            }
        }
    }
}
