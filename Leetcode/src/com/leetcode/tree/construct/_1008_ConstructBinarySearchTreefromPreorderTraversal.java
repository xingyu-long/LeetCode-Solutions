package com.leetcode.tree.construct;

import com.leetcode.common.TreeNode;

/**
 * @Date: 05/24/2020
 * @Description: BST, construct
 **/
public class _1008_ConstructBinarySearchTreefromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return dfs(preorder, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] pre, int left, int right) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(pre[left]);
        // find;
        int index = left + 1; // 这里应该是left + 1开始
        for (index = left + 1; index <= right; index++) {
            if (pre[index] > pre[left]) {
                break;
            }
        }
        root.left = dfs(pre, left + 1, index - 1);
        root.right = dfs(pre, index, right);
        return root;
    }
}
