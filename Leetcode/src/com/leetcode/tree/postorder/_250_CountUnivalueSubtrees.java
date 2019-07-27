package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _250_CountUnivalueSubtrees {

    /**
     *  250. Count Univalue Subtrees
     Given a binary tree, count the number of uni-value subtrees.

     A Uni-value subtree means all nodes of the subtree have the same value.

     For example:
     Given binary tree,
          5
         / \
        1   5
       / \   \
      5   5   5
     return 4.

     root = 5 res = 2
     root = 1
     root = 5 res = 3
     root = 5 res = 4
     */
    private int res;
    // time:O(n) space:O(n)
    public int countUnivalSubtrees(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }

    public boolean helper(TreeNode root) {
        // 那种有一个子树的情况如何做决定？

        if (root == null) return true;
        boolean left = helper(root.left);
        boolean right = helper(root.right);

        if (left && right) {
            //验证左右子树不为null的情况是否与root相同
            if (root.left != null && root.val != root.left.val) {
                return false;
            }
            if (root.right != null && root.val != root.right.val) {
                return false;
            }
            res++;
            return true;
        }
        return false;
    }
}