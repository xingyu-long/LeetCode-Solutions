package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _216_CombinationSumIII {

    /**
     * 216. Combination Sum III
     * When: 2019/04/29
     *
     * solution:
     * 跟前面的方法基本差不多，但是这里多一个条件就是这里n==0
     *
     * time: O(2^n)
     * space: O(n)
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<Integer>(), k, n, 1);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int k, int n, int start) {
        if (n == 0 && k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 这里是九个数 1,2,3,4,5,6,7,8,9都可以选择
        for (int i = start; i <= 9; i++) {
            list.add(i);
            helper(res, list, k - 1, n - i, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
