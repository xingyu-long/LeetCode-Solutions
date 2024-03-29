/*
 * @Date: 08/19/2021 19:05:04
 * @LastEditTime: 08/19/2021 19:08:20
 * @Description: You need to fill out
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _1339_MaximumProductOfSplittedBinaryTree {
    private int MOD = (int) (Math.pow(10, 9) + 7);
    private long res = 0, total = 0;
    private long sub;
    // time: O(n), space: O(n)
    // 值的处理需要注意
    public int maxProduct(TreeNode root) {
        // 先算总和 然后在每个点去尝试分开？
        total = dfs(root);
        dfs(root);
        return (int) (res % MOD);
    }

    private long dfs(TreeNode root) {
        if (root == null)
            return 0;
        sub = root.val + dfs(root.left) + dfs(root.right);
        res = Math.max(res, (total - sub) * sub);
        return sub;
    }
}
