package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _114_FlattenBinaryTreetoLinkedList {

    /**
     *  114. Flatten Binary Tree to Linked List
     *  When:2019/7/16
     *  Difficulty: Medium
     *
     * @param root
     */

    //利用stack来遍历 time:O(n) space:O(n)
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
            if (!stack.isEmpty()) {
                cur.right = stack.peek();
            }
            cur.left = null;
        }
    }

    // 使用recursive的形式来做
    // https://www.youtube.com/watch?v=NHdrzNpt1ZI
    public void flatten2(TreeNode root) {
        if (root == null) return;
        flatten2(root.left);
        flatten2(root.right);

        if (root.left == null) return; //表明左边已经排好了

        TreeNode left = root.left;
        while (left.right != null) {
            left = left.right;// 走到左子树的叶子节点
        }
        left.right = root.right;
        root.right = root.left;
        root.left = null;
    }


}
