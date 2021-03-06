package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/04/29, 2019/7/15, 2019/10/7, 06/08/2020
 * @Description: Backtracking
 **/
public class _77_Combinations {

    // time: c_{n}^{k}, space:O(k)
    public List<List<Integer>> combine(int n, int k) {
        // 普通写的话，不能表示多个k的情况，这样复杂度会受到影响
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> list, int k, int n, int index) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
        }

        for (int i = index; i <= n; i++) {
            if (list.size() > k) {
                continue; // 这样可以不用计算多余大于k大小的情况
            }
            list.add(i);
            dfs(res, list, k, n, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
