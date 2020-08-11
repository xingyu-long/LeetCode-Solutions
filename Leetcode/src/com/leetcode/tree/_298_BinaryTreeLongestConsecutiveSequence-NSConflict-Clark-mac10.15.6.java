package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 06/02/2020
 * @Description: Tree
 **/
public class _298_BinaryTreeLongestConsecutiveSequence {

    /**
     * 298. Binary Tree Longest Consecutive Sequence When:2019/7/26 review1:2019/9/14 Difficulty:
     * Medium Given a binary tree, find the length of the longest consecutive sequence path.
     * <p>
     * The path refers to any sequence of nodes from some starting node to any node in the tree
     * along the parent-child connections. The longest consecutive path need to be from parent to
     * child (cannot be the reverse). solution: 利用当前值+1作为target 来寻找是否符合 不符合max = 1符合max++并且更新res
     *
     * @param root
     * @return
     */
    // time:O(n) space:O(n)
    private static int res = 0;

    // preorder
    public static int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, 0, root.val);
        return res;
    }

    public static void helper(TreeNode root, int max, int next) {
        if (root == null) {
            return;
        }
        if (root.val == next) {
            max++;
        } else {
            max = 1;
        }
        res = Math.max(res, max);
        helper(root.left, max, root.val + 1);
        helper(root.right, max, root.val + 1);
    }

    // postorder
    public int longestConsecutive2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 1 + dfs(root.left);
        int right = 1 + dfs(root.right);
        if (root.left != null && root.left.val != root.val + 1) {
            left = 1;
        }

        if (root.right != null && root.right.val != root.val + 1) {
            right = 1;
        }
        res = Math.max(res, Math.max(left, right));
        return Math.max(left, right);
    }
}
