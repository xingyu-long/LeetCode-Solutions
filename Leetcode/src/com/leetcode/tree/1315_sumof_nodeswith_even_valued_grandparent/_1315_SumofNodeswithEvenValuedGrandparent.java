package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _1315_SumofNodeswithEvenValuedGrandparent {
    public int res = 0;
    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) return res;
        helper(root);
        return res;
    }

    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (root.left != null && root.val % 2 == 0) {
            if (root.left.left != null) res += root.left.left.val;
            if (root.left.right != null) res += root.left.right.val;
        }
        helper(root.right);
        if (root.right != null && root.val % 2 == 0) {
            if (root.right.left != null) res += root.right.left.val;
            if (root.right.right != null) res += root.right.right.val;
        }
    }
}
