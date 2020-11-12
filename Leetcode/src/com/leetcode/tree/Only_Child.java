package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class Only_Child {
    private int res;

    public int solve(TreeNode root) {
        if (root == null)
            return 0;
        res = 0;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        if ((root.left == null && root.right != null) ||
                (root.left != null && root.right == null)) {
            res++;
        }
        dfs(root.left);
        dfs(root.right);
    }
}
