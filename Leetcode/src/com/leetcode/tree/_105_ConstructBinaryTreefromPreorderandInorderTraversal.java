package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _105_ConstructBinaryTreefromPreorderandInorderTraversal {

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * When:2019/8/25
     *
     * https://www.youtube.com/watch?v=S1wNG5hx-30
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        // 先序排列里面的第一个元素就是root
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        //分为左子树和右子树
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        // 相隔了左子树的部分则就是inIndex - inStart + 1
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}

