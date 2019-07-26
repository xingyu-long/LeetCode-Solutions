package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _110_BalancedBinaryTree {

    /**
     *  110. Balanced Binary Tree
     *  When: 2019/04/18
     *  Review1:2019/7/26
     * solution：
     * 利用后序遍历，计算其深度并且计算左右子树的差值。
     *
     * 也可以使用之前的先计算出左右两个子树的maxDepth 然后差值
     * @param root
     * @return
     */

    // time:O(n)
    // bottom-up
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

    // https://leetcode.com/problems/balanced-binary-tree/discuss/35691/The-bottom-up-O(N)-solution-would-be-better
    // time:O(n^2) 每次比较的时候也重新查找了maxDepth
    // top-down
    public boolean isBalanced2(TreeNode root) {
        if (root == null) return true;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.abs(left - right) <= 1 && isBalanced2(root.left) && isBalanced2(root.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
