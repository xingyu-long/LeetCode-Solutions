package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _156_BinaryTreeUpsideDown {
    /**
     * 156. Binary Tree Upside Down
     * When:2019/9/22
     * Difficulty: Medium
     * 这个对于理解recursion有好处。
     * @param root
     * @return
     */
    // 题目说了右子树只存在叶子节点，这也是为啥一直用左递归的就可以。
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode newRoot = upsideDownBinaryTree(left); // 左节点变为根
        left.left = right; // 新的根的左边就是原来的右节点
        left.right = root; // 新的根的右边就是原来的根节点

        root.left = null;
        root.right = null;
        return newRoot;
    }
}
