package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _465_OptimalAccountBalancing {
    public int res;

    public int minTransfers(int[][] transactions) {
        res = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] trans : transactions) {
            map.put(trans[0], map.getOrDefault(trans[0], 0) - trans[2]);
            map.put(trans[1], map.getOrDefault(trans[1], 0) + trans[2]);
        }
        List<Integer> debt = new ArrayList<>();
        for (int val : map.values()) {
            if (val != 0) debt.add(val);
        }
        helper(debt, 0, 0);
        return res;
    }

    public void helper(List<Integer> debt, int start, int count) {
        while (start < debt.size() && debt.get(start) == 0) {
            start++;// 因为backtracking的时候有修改值。
        }
        if (start == debt.size()) {
            res = Math.min(res, count);
            return;
        }

        for (int i = start + 1; i < debt.size(); i++) {
            // 这里的backtracking一个元素只能被用一次。
            if (debt.get(start) * debt.get(i) < 0) {
                debt.set(i, debt.get(i) + debt.get(start));
                helper(debt, start + 1, count + 1);
                debt.set(i, debt.get(i) - debt.get(start));
            }
        }
    }


}