package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _99_RecoverBinarySearchTree {

    /**
     * 99. Recover Binary Search Tree
     * When:2019/9/22
     * Difficulty: Hard
     */
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;


    // space: O(n)
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        helper(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    // DFS https://www.youtube.com/watch?v=QZMropFflv4
    // 看前面的讲解就可以了
    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (prev != null && prev.val >= root.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        helper(root.right);
    }

    // BFS
    public void recoverTree2(TreeNode root) {
        if (root == null) return;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (prev != null && cur.val <= prev.val) {
                    if (first == null) first = prev;
                    second = cur;
                }
                prev = cur;
                cur = cur.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
