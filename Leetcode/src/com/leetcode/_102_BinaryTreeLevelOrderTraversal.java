package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _102_BinaryTreeLevelOrderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 145. 102. Binary Tree Level Order Traversal
     * When: 2019/04/12
     *
     * solution:
     * 同样使用先序遍历 只是需要考虑到depth
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, 0, res);
        return res;
    }

    public static void helper(TreeNode root, int depth, List<List<Integer>> res) {
        if (root == null) return;

        // 考虑到res的size就是指当前的深度
        if (res.size() == depth) {
            ArrayList<Integer> cur = new ArrayList<>();
            cur.add(root.val);
            res.add(cur);
        } else {
            List<Integer> cur = res.get(depth);
            cur.add(root.val);
        }
        helper(root.left, depth+1, res);
        helper(root.right, depth+1, res);
    }
}
