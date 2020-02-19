package com.leetcode.backtracking;

import java.util.*;

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
    //time:O(2^candidate) space: O(candidate)
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
        // 利用每次按照i开始，消除duplicate因为不能让后面的数再用前面的情况，最多可用到当前的数字
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }

    // 这个是有些小问题，先不纠结了。后面再看。
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target == 0) return new LinkedList<>();
        HashMap<String, List<List<Integer>>> map = new HashMap<>();
        Arrays.sort(candidates);
        return dfs(candidates, target, 0, map);
    }

    public List<List<Integer>> dfs(int[] candidates, int target, int index, HashMap<String, List<List<Integer>>> map) {
        String key = index + " " + target;
        List<List<Integer>> res = new LinkedList<>();
        if (target == 0) {
            res.add(new LinkedList<>());
            return res;
        }
        if (target < 0) return res;
        if (map.get(key) != null) return map.get(key);
        for (int i = index; i < candidates.length; i++) {
            List<List<Integer>> rest = dfs(candidates, target - candidates[i], i, map);
            for (List<Integer> temp : rest) {
                temp.add(0, candidates[i]);
                res.add(new LinkedList<>(temp));
            }
        }
        map.put(key, res);
        return res;
    }

    public static void main(String[] args) {
        _39_CombinationSum combinationSum = new _39_CombinationSum();
        int[] candidate = {2,3,7};
        int target = 7;
        List<List<Integer>> res = combinationSum.combinationSum2(candidate, target);
        for (List<Integer> temp : res) {
            for (int num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
