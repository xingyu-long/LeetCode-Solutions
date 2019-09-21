package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _298_BinaryTreeLongestConsecutiveSequence {

    /**
     * 298. Binary Tree Longest Consecutive Sequence
     * When:2019/7/26
     * review1:2019/9/14
     * Difficulty: Medium
     * Given a binary tree, find the length of the longest consecutive sequence path.
     * <p>
     * The path refers to any sequence of nodes from some starting node to any node
     * in the tree along the parent-child connections. The longest consecutive path need to
     * be from parent to child (cannot be the reverse).
     * solution: 利用当前值+1作为target 来寻找是否符合 不符合max = 1符合max++并且更新res
     *
     * @param root
     * @return
     */
    // time:O(n) space:O(n)
    private static int res = 0;

    public static int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        helper(root, 0, root.val);
        return res;
    }

    public static void helper(TreeNode root, int max, int target) {
        if (root == null) return;
        if (root.val == target) {
            max++;
        } else {
            max = 1;
        }
        res = Math.max(res, max);
        helper(root.left, max, root.val + 1);
        helper(root.right, max, root.val + 1);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(5);
        root.right.left.left.left = new TreeNode(6);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        System.out.println(longestConsecutive(root));
    }
}
