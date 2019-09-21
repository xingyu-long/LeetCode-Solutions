package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _687_LongestUnivaluePath {

    /**
     * 687. Longest Univalue Path
     * When:2019/9/15
     * @param root
     * @return
     */
    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return res;
    }

    public int helper(TreeNode root) {
        if (root == null) return 0;
        int l = helper(root.left);
        int r = helper(root.right);
        int pl = 0;
        int pr = 0;
        if (root.left != null && root.val == root.left.val) {
            pl = l + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            pr = r + 1;
        }
        res = Math.max(res, pl + pr);
        return Math.max(pl, pr);
    }
}
