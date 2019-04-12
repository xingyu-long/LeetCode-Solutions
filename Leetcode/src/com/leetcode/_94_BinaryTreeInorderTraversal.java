package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _94_BinaryTreeInorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 94. Binary Tree Inorder Traversal
     * When: 2019/04/12
     *
     * solution:
     * 最普通的中排序，并且这里的输入是root节点而不是数组所以自己不用构造才对。
     * 中序输出，则就是左遍历到底 输出值 然后中间值 再右遍历 再输出值
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }

    public static void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        TreeNode right = root.right;
        right.left = new TreeNode(3);
        System.out.println(inorderTraversal(root).toString());
    }
}
