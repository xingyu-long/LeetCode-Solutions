package com.leetcode.tree;

public class _96_UniqueBinarySearchTrees {

    /**
     * 96. Unique Binary Search Trees
     * When: 2019/04/26
     *
     * solution:
     * 利用dp的想法来做，因为涉及到子问题
     *
     * test case:
     *    n  = 3;
     *     1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     * root : 1 left: 0 right: 2 f(0) * f(2) （这里相乘 左右的不同情况的乘积则就是全部情况）
     * root : 2 left: 1 right: 1 f(1) * f(1)
     * root : 3 left: 2 right: 0 f(2) * f(0)
     *
     * 所以f(n) = f(0) * f(n-1) + f(1) * f(n-2) + ... f(n-1) * f(0)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int  i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                //j表示左子树的个数 i表示全部
                res[i] += res[j] * res[i - j - 1];
            }
        }
        return res[n];
    }
}
