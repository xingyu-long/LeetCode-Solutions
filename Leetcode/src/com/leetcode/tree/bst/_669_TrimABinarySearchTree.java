/*
 * @Date: 02/02/2021 11:17:03
 * @LastEditTime: 09/06/2021 15:12:44
 * @Description: BST
 */
package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

public class _669_TrimABinarySearchTree {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null)
            return null;

        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }
}
