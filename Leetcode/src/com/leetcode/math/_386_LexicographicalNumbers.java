package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * _386_LexicographicalNumbers
 */
public class _386_LexicographicalNumbers {

    // backtracking。
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(res, n, i);
        }
        return res;
    } 
    
    public void dfs(List<Integer> res, int n, int cur) {
        if (cur > n) return;
        else {
            res.add(cur);
            for (int i = 0; i < 10; i++) {
                if (cur * 10 + i > n) break;
                dfs(res, n, cur * 10 + i);
            }
        }
    }
}