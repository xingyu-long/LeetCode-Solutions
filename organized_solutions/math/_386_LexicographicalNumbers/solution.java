/*
 * @Date: 12/21/2019 17:32:36
 * @LastEditTime: 12/06/2020 09:49:02
 * @Description: Sort/Backtracking
 */
package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class _386_LexicographicalNumbers {

    // Time: O(N) space: O(1)
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(res, n, i);
        }
        return res;
    }

    public void dfs(List<Integer> res, int n, int cur) {
        if (cur > n)
            return;
        else {
            res.add(cur);
            for (int i = 0; i < 10; i++) {
                if (cur * 10 + i > n)
                    break;
                dfs(res, n, cur * 10 + i);
            }
        }
    }
}