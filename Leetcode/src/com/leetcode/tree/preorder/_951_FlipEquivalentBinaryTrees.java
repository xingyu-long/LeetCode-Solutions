package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

public class _951_FlipEquivalentBinaryTrees {
    // time:O(n) space:O(n)
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null) return false;
        if (root2 == null) return false;
        if (root1.val != root2.val) return false;// 题目的意思是有的翻转，有的不翻转
        return (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left))
                || (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right));
    }
}
