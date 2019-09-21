package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _543_DiameterofBinaryTree {

    private static int res = 0;

    /**
     * 543. Diameter of Binary Tree
     * When:2019/9/15
     * Difficulty: Easy
     * 最大的长度等于左右子树最深的深度相加
     * @param root
     * @return
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return res;
    }

    // time:O(n) space:O(h)
    // https://www.youtube.com/watch?v=VuezJmuIyU4
    public static int helper(TreeNode root) {
        if (root == null) return 0; // 这里设置为0，则只要有一个点就是+1这种存在。但是这个题求的是edge 就是两个点的话 结果只有1
        int left = helper(root.left);
        int right = helper(root.right);
        // 这个在 +1 操作之前，所以就符合要求
        res = Math.max(res, left + right);// 这里需要注意计数的问题
        return Math.max(left, right) + 1;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(diameterOfBinaryTree(root));
        System.out.println(maxDepth(root));
    }
}
