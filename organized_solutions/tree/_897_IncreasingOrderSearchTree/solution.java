/*
 * @Date: 12/03/2020 10:24:58
 * @LastEditTime: 12/03/2020 10:27:58
 * @Description: inorder traversal
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _897_IncreasingOrderSearchTree {
    private TreeNode res;
    private TreeNode prev;
    // Time: O(N) Space: O(N)
    public TreeNode increasingBST(TreeNode root) {
        res = null;
        prev = null;
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (res == null) {
            res = root;
        } else {
            prev.right = root;
        }
        prev = root;
        root.left = null;
        dfs(root.right);
    }

    // 这个不是那么容易理解。
    public TreeNode increasingBST2(TreeNode root) {
        return increasingBST(root, null);
    }

    public TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, tail);
        return res;
    }
}
