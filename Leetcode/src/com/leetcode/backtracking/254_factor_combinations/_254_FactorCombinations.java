package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _254_FactorCombinations {
    /**
     * 254. Factor _77_Combinations
     * When:2019/7/15
     * review1:2019/10/13
     * Difficulty: Medium
     * Numbers can be regarded as product of its factors. For example,
     *
     * @param n
     * @return
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), n, 2);
        return res;
    }

    // 利用除法
    private void dfs(List<List<Integer>> res, List<Integer> list, int n, int start) {
        // goal
        if (n == 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<>(list));
                return;
            }
        }

        // our choice
        for (int i = start; i <= n; i++) {
            if (n % i == 0) { // our constraint
                list.add(i);
                dfs(res, list, n / i, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
