package com.leetcode.tree.construct;

import com.leetcode.common.TreeNode;

public class _105_ConstructBinaryTreefromPreorderandInorderTraversal {
    // https://www.youtube.com/watch?v=S1wNG5hx-30
    // time:O(N) space:O(N)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder,
            int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        // split the inorder
        int i = 0;
        for (i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val)
                break;
        }
        // 主要是计算长度的哪个部分。
        // preEnd = preStart + # of element - 1转为坐标。
        int len = i - inStart;
        root.left = buildTree(preorder, preStart + 1, preStart + len, inorder,
                inStart, i - 1);
        root.right = buildTree(preorder, preStart + (1 + len), preEnd, inorder,
                i + 1, inEnd);
        return root;
    }
}

