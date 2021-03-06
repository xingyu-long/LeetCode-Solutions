package com.leetcode.array;

import java.util.Arrays;

/**
 * @Date: 08/25/2020
 * @Description: Graph, array.
 **/
public class _277_FindtheCelebrity {

    // 如果候选i知道j或者j不知道i，则表示i不能为候选。
    // time: O(N^2)
    public int findCelebrity1(int n) {
        boolean[] candidates = new boolean[n];
        Arrays.fill(candidates, true);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (candidates[i] && i != j) {
                    if (knows(i, j) || !knows(j, i)) {
                        candidates[i] = false;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (candidates[i]) {
                return i;
            }
        }
        return -1;
    }

    // 上面的简化版，如果遇到i不是候选的情况，直接break。最后看j是否为n（表示满足条件）
    // 加入了break，优化了过程。
    public int findCelebrity2(int n) {
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (knows(i, j) || !knows(j, i)) {
                    break;
                }
            }
            if (j == n) {
                return i;
            }
        }
        return -1;
    }

    // 先假定是0这个为celebrity 然后只要有人认识就更新res（因为只有这样才可能称为celebrity）
    public int findCelebrity(int n) {
        if (n < 2) {
            return -1;
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (knows(res, i)) {
                res = i;
            }
        }
        for (int i = 0; i < n; i++) {
            // 表示如果res认识其中一个就失败或者其中有人不认识res也失败
            if (res == i) continue;
            if (knows(res, i) || !knows(i, res)) {
                return -1;
            }
        }
        return res;
    }

    // 防止报错写上的
    public boolean knows(int a, int b) {
        return true;
    }

}
