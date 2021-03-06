package com.leetcode.array;

/**
 * @Date: 07/15/2020
 * @Description: Array,
 **/
public class _1503_LastMomentBeforeAllAntsFallOutofaPlank {
    // 主要是能够将题目的意思简单化：不管对于哪个点，都是最终要走出去，直接考虑不碰撞转向的情况
    // time:O(N)
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;
        for (int num : left) {
            res = Math.max(res, num);
        }
        for (int num : right) {
            res = Math.max(res, n - num);
        }
        return res;
    }
}
