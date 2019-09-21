package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _110_BalancedBinaryTree {

    /**
     *  110. Balanced Binary Tree
     *  When: 2019/04/18
     *  Review1:2019/7/26
     *  review2: 2019/9/10
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
        depth = helper(root);
        return depth != -1;
    }
    // 解释可以看这个视频 https://www.youtube.com/watch?v=LU4fGD-fgJQ&list=PLiQ766zSC5jND9vxch5-zT7GuMigiWaV_&index=7
    public static int helper(TreeNode root) {
        if (root == null) return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        if (left == -1 || right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

    // https://leetcode.com/problems/balanced-binary-tree/discuss/35691/The-bottom-up-O(N)-solution-would-be-better
    // time:O(nlogn) n个点，每一次都有查找depth的操作，logN
    // top-down
    public boolean isBalanced2(TreeNode root) {
        if (root == null) return true;
        int leftN = maxDepth(root.left);
        int rightN = maxDepth(root.right);
        int diff = Math.abs(leftN - rightN);
        if (diff > 1) return false;
        else return isBalanced2(root.left) && isBalanced2(root.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
