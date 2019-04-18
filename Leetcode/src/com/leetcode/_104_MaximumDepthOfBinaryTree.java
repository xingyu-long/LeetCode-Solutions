package com.leetcode;

public class _104_MaximumDepthOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 104. Maximum Depth of Binary Tree
     * When: 2019/04/18
     *
     * solution：
     * 1. 可以使用先序遍历来做
     * 2. 跟111的思路一模一样 但是不需要里面的if 因为都是math.max
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
