package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _106_ConstructBinaryTreefromInorderandPostorderTraversal {
    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     * When:2019/8/25
     * Difficulty: medium
     * 使用和105一样的idea
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0
                || postorder == null || postorder.length == 0) return null;
        return helper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode helper(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }

        root.left = helper(inorder, postorder, inStart, index - 1, postStart, postStart + index - inStart - 1);
        root.right = helper(inorder, postorder, index + 1, inEnd, postStart + index - inStart, postEnd - 1);
        return root;
    }
}
