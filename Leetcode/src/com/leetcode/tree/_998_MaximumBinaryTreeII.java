/*
 * @Date: 2020-04-02 15:42:10
 * @LastEditors: Clark long
 * @LastEditTime: 2020-04-02 15:42:39
 */
package com.leetcode.tree;

public class _998_MaximumBinaryTreeII {
    // time:O(n) space:O(n)
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        // it's kind of BST? 
        // if (root == null) return null;
        if (root != null && root.val > val) {
            root.right = insertIntoMaxTree(root.right, val); 
            // 带有递归并且改变right的之前不熟悉
            return root;
        } 
        // 当val 大于root的值或者root为空（走到了最后）
        TreeNode node = new TreeNode(val);
        node.left = root;
        return node;
    }
}

