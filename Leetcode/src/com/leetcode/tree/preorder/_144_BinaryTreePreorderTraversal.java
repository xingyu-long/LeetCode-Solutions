package com.leetcode.tree.preorder;

import java.util.ArrayList;
import java.util.List;

public class _144_BinaryTreePreorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 144. Binary Tree Preorder Traversal
     * When: 2019/04/12
     *
     * solution:
     * 最普通的先序排序，并且这里的输入是root节点而不是数组所以自己不用构造才对。
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }

    public static void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        res.add(root.val);
        helper(res, root.left);
        helper(res, root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        TreeNode right = root.right;
        right.left = new TreeNode(3);
        System.out.println(preorderTraversal(root).toString());
    }
}
