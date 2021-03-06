package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

public class _530_MinimumAbsoluteDifferenceinBST {
    public int res;
    public int prev;

    public int getMinimumDifference(TreeNode root) {
        res = Integer.MAX_VALUE;
        prev = -1;
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null)
            return;

        dfs(root.left);
        if (prev != -1) {
            res = Math.min(res, Math.abs(root.val - prev));
        }
        prev = root.val;
        dfs(root.right);

    }
}