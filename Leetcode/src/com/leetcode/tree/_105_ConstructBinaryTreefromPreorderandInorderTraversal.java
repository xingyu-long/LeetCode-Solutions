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
        return helperWithPreaAnIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode helperWithPreaAnIn(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        // split the inorder
        int i = 0;
        for (i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) break;
        }
        // 主要是计算长度的哪个部分。
        // preEnd = preStart + # of element - 1转为坐标。
        root.left = helperWithPreaAnIn(preorder, preStart + 1, preStart + (i - inStart), inorder, inStart, i - 1);
        root.right = helperWithPreaAnIn(preorder, preStart + (1 + i - inStart), preEnd, inorder, i + 1, inEnd);
        return root;
    }
}

