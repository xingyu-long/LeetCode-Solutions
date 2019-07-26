package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _298_BinaryTreeLongestConsecutiveSequence {

    /**
     *  298. Binary Tree Longest Consecutive Sequence
     *  When:2019/7/26
     *  Difficulty: Medium
     Given a binary tree, find the length of the longest consecutive sequence path.

     The path refers to any sequence of nodes from some starting node to any node
     in the tree along the parent-child connections. The longest consecutive path need to
     be from parent to child (cannot be the reverse).

     For example,
          1
          \
           3
          / \
         2  4
             \
             5
     Longest consecutive sequence path is 3-4-5, so return 3.
             2
              \
              3
             /
            2
           /
          1
     Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

     solution: 利用当前值+1作为target 来寻找是否符合 不符合max = 1符合max++并且更新res
     * @param root
     * @return
     */
    // time:O(n) space:O(n)
    private int res = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        helper(root, 0, root.val);
        return res;
    }

    public void helper(TreeNode root, int max, int target) {
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
}
