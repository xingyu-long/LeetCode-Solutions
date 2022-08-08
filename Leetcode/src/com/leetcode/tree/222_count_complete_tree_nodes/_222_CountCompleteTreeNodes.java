package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _222_CountCompleteTreeNodes {
    /**
     * 222. Count Complete Tree Nodes
     * When:2019/9/24
     * Difficulty: Medium
     * @param root
     * @return
     */
    // 利用完全二叉树和完美二叉树的性质 如果是完美二叉树，就直接用2^height - 1如果不是那就分别求左右子树的情况并且加上当前的1个
    public int countNodes(TreeNode root) {
        int left = leftDepth(root);
        int right = rightDepth(root);
        if (left == right) {
            return (int) Math.pow(2, left) - 1;
        } else {
            return  1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    public int leftDepth(TreeNode root) {
        int res = 0;
        while (root != null) {
            root = root.left;
            res++;
        }
        return res;
    }

    public int rightDepth(TreeNode root) {
        int res = 0;
        while (root != null) {
            root = root.right;
            res++;
        }
        return res;
    }
}
