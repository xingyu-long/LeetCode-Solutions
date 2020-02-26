package com.leetcode.tree;

import com.leetcode.common.TreeNode;

/**
 * _437_PathSumIII
 */
public class _437_PathSumIII {

    // 选取当前节点或者不选取当前节点。 思路正确，但写出来有点点问题
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    public int helper(TreeNode root, int sum) {
        int res = 0;
        if (root == null) return 0;
        if (sum == root.val) res++; // 因为后面可能会有多的情况，因为这里可以加上负数，也会导致0.
        res += helper(root.left, sum - root.val) + helper(root.right, sum - root.val);
        return res;
    }
}