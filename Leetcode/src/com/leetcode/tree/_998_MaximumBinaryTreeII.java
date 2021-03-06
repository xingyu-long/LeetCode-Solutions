/*
 * @Date: 2020-04-02 15:42:10
 * @LastEditors: Clark long
 * @LastEditTime: 2020-04-02 15:42:39
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

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

    // 自己写的这个更加易懂
    // 加入的这个元素 往右走，因为是append
    public TreeNode insertIntoMaxTree2(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val >= val) {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        } else {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
    }
}

