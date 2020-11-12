package com.leetcode.dynamicProgramming;

/**
 * @Date: 09/02/2020
 * @Description: DP
 **/
public class _96_UniqueBinarySearchTrees {
    // https://www.cnblogs.com/grandyang/p/4299608.html
    public int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                //j表示左子树的个数 i表示全部
                res[i] += res[j] * res[i - j - 1];
            }
        }
        return res[n];
    }
}
