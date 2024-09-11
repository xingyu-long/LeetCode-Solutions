package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

public class _701_InsertintoaBinarySearchTree {
    // time: O(logN)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val) {
            // go right;
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                root.right = insertIntoBST(root.right, val);
            }
        } else {
            // go left;
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                root.left = insertIntoBST(root.left, val);
            }
        }
        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val) {
            // go right;
            root.right = insertIntoBST2(root.right, val);
        } else {
            // go left;
            root.left = insertIntoBST2(root.left, val);
        }
        return root;
    }
}
