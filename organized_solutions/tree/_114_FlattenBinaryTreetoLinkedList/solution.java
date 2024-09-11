package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * @Date: 2019/7/16, 2019/9/23, 06/19/2020
 * @Description: Tree
 **/
public class _114_FlattenBinaryTreetoLinkedList {

    //利用stack来遍历 time:O(n) space:O(n) preorder
    //感觉会比较清楚。每次都连接stack的栈顶元素
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (!stack.isEmpty()) {
                cur.right = stack.peek();
            }
            cur.left = null;
        }
    }

    // 使用recursive的形式来做
    // https://www.youtube.com/watch?v=NHdrzNpt1ZI
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten2(root.left);
        flatten2(root.right);

        if (root.left == null) {
            return; //表明左边已经排好了
        }

        TreeNode left = root.left;
        // 这一步需要找到最右边的那个。
        while (left.right != null) {
            left = left.right;// 走到左子树的叶子节点
        }
        left.right = root.right;
        root.right = root.left;
        root.left = null;
    }

    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten3(root.left);
        flatten3(root.right);

        if (root.left != null) {
            TreeNode next = root.left;
            while (next.right != null) {
                next = next.right;
            }
            next.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
}
