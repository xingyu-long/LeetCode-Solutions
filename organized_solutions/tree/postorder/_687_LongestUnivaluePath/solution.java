/*
 * @Date: 2019-09-15 16:14:17
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 15:06:56
 */
package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _687_LongestUnivaluePath {
    
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
        res = Math.max(res, pl + pr); // 相当于选择连续的那个边。如果没有相同的，也会返回0
        return Math.max(pl, pr);
    }
}
