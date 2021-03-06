package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _563_BinaryTreeTilt {
    public int res;

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        res = 0;
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        res += Math.abs(left - right);
        return left + right + root.val;
    }
}