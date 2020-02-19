package com.leetcode.tree;


import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _111_MinimumDepthOfBinaryTree {

    /**
     * 111. Minimum Depth of Binary Tree
     * When: 2019/04/18
     * Review1: 2019/7/26
     * review2: 2019/9/14
     * solution：
     * 1. 依然使用递归的先序遍历 然后这里不是加val而是x += 1即可
     *
     * @param root
     * @return
     */
    // time:O(n), space: O(n)
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left) + 1;
        int right = minDepth(root.right) + 1;

        if (root.left == null) return right;
        if (root.right == null) return left;
        return Math.min(left, right);
    }

    // BFS
    public static int minDepth3(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }

    // 基本上是一样的思路。
    public int minDepth4(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left != 0 && right != 0) return Math.min(left, right) + 1;
        else if (left == 0) return right + 1;
        return left + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);

        TreeNode leftOne = new TreeNode(9);
        TreeNode rightOne = new TreeNode(0);

        root.left = leftOne;
        root.right = rightOne;
//
        leftOne.left = new TreeNode(5);
        leftOne.right = new TreeNode(1);
        System.out.println(minDepth3(root));
    }
}
