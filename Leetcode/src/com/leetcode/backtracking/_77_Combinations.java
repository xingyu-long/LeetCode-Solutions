package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _77_Combinations {

    /**
     *  77. Combinations
     *  When: 2019/04/29
     *  Review1: 2019/7/15
     *  Difficulty: Medium
     *
     * solution: 回溯法
     * 主要考虑到个数问题，然后使用k-1来计数插入的长度
     *
     * space: O(n)
     * time: O(n^min{k,n-k})
     * https://stackoverflow.com/questions/31120402/complexity-when-generating-all-combinations
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        // 普通写的话，不能表示多个k的情况，这样复杂度会受到影响
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int n, int k, int start) {
        // 也可以不改变k的值 直接用list.size() == k
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            helper(res, list, n, k - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
