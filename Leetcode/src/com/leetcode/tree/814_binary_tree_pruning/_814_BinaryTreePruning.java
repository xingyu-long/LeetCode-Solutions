package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _814_BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    public boolean containsOne(TreeNode root) {
        if (root == null)
            return false;
        boolean left = containsOne(root.left);
        boolean right = containsOne(root.right);
        if (!left)
            root.left = null;
        if (!right)
            root.right = null;
        return root.val == 1 || left || right;
    }
}