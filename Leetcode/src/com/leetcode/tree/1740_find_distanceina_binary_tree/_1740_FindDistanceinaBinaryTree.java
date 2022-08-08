/*
 * @Date: 07/17/2022 17:41:11
 * @LastEditTime: 07/17/2022 17:41:36
 * @Description: Tree, distance
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _1740_FindDistanceinaBinaryTree {
    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = findLCA(root, p, q);
        int lcaToP = dfs(lca, p);
        int lcaToQ = dfs(lca, q);
        return lcaToP + lcaToQ;
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) {
            return -1;
        }
        if (root.val == val) {
            return 0;
        }
        int left = dfs(root.left, val);
        // we found the node from left side
        if (left != -1) {
            return left + 1;
        }
        // we found the node from right side
        int right = dfs(root.right, val);
        if (right != -1) {
            return right + 1;
        }
        return -1;
    }

    public TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
