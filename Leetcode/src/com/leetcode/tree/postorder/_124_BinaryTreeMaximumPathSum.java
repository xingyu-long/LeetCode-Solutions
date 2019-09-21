package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _124_BinaryTreeMaximumPathSum {

    static int res; // 这样有利于保存

    /**
     *  124. Binary Tree Maximum Path Sum
     *  When: 2019/04/18
     *  Review1: 2019/7/27
     * solution:
     * 主要是利用其后序遍历（postOrder) 然后注意"里面的return应该是取左右边最大的一部分加上root.val"
       1
     /  \
     2  3
     \
     5



     * time: O(n)
     * space: O(n)
     * @param root
     * @return
     */
    // time:O(n) space:O(n) call stack
    // http://zxi.mytechroad.com/blog/tree/leetcode-124-binary-tree-maximum-path-sum/
    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }

    public static int helper(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        int sum = left + right + root.val; // 这个作为结果就是求左边最大的情况+当前节点+右边最大情况
        res = Math.max(res, sum);
        return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        maxPathSum(root);
    }
}
