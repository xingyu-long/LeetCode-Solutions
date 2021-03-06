package com.leetcode.tree;

import com.leetcode.common.TreeNode;

/**
 * _1372_LongestZigZagPathinaBinaryTree
 */
public class _1372_LongestZigZagPathinaBinaryTree {

    public int res = 0;
    // TLE
    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        travel(root);
        return res - 1;
    }
    
    public void travel(TreeNode root) {
        if (root == null) return;
        dfs(root, true, 0);
        dfs(root, false, 0);
        travel(root.left);
        travel(root.right);
    }
    
    public void dfs(TreeNode root, boolean isLeft, int level) {
        if (root == null) {
            res = Math.max(res, level);
            return;
        }
        if (isLeft) {
            dfs(root.right, false, level + 1);        
        } else {
            dfs(root.left, true, level + 1);
        }
    }
}