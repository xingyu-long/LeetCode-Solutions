package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _99_RecoverBinarySearchTree {

    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;
    // prev应该是在中序里面设置！！！记住
    // first 需要check是否为null，为了记录第一个first！！！
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

    // Morris Traversal https://www.youtube.com/watch?v=wGXB9OWhPTg
    public void morrisTraversal(TreeNode head) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        TreeNode temp = null;
        TreeNode root = head;
        while (root != null) {
            if (root.left == null) {
                if (prev != null && prev.val > root.val) {
                    if (first == null) first = prev;
                    second = root;
                }
                prev = root;
                root = root.right;
            } else {
                temp = root.left;
                // 找predecessor
                while (temp.right != null && temp.right != root) {
                    temp = temp.right;
                }
                // 表示之前建立了链接，现在需要访问root然后删除这个关系
                if (temp.right != null) {
                    temp.right = null;
                    if (prev != null && prev.val > root.val) {
                        if (first == null) first = prev;
                        second = root;
                    }
                    prev = root;
                    root = root.right;// 返回上一层
                } else {
                    temp.right = root;
                    root = root.left;
                }
            }
        }
        if (first != null && second != null) {
            int num = first.val;
            first.val = second.val;
            second.val = num;
        }
    }
}
