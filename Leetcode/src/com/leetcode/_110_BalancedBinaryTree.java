package com.leetcode;

public class _110_BalancedBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 110. Balanced Binary Tree
     * When: 2019/04/18
     *
     * solution：
     * 利用后序遍历，计算其深度并且计算左右子树的差值。
     *
     * 也可以使用之前的先计算出左右两个子树的maxDepth 然后差值
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int depth = 0;
        depth = maxDepth(root, depth);
        return depth != -1;
    }

    public static int maxDepth(TreeNode root, int depth) {
        if (root == null) return 0;

        int left = maxDepth(root.left, depth);
        int right = maxDepth(root.right, depth);

        if (left == -1 || right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;

        depth = Math.max(left, right) + 1;

        return depth;
    }
}
