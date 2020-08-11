package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

/**
 * @Date: 05/08/2020
 * @Description: Tree, preorder
 **/
public class _572_SubtreeofAnotherTree {
    // time:O(ST) space:O(S + T)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        if (isSame(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
