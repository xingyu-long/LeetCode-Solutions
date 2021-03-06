package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _606_ConstructStringfromBinaryTree {
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        String res = t.val + "";

        String left = tree2str(t.left);
        String right = tree2str(t.right);

        if (left.equals("") && right.equals(""))
            return res;
        if (left.equals(""))
            return res + "()" + "(" + right + ")";
        if (right.equals(""))
            return res + "(" + left + ")";
        return res + "(" + left + ")" + "(" + right + ")";
    }
}