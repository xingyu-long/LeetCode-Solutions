package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _270_ClosestBinarySearchTreeValue {

    /**
     *  270. Closest Binary Search Tree Value
     *
     Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

     Note:

     Given target value is a floating point.
     You are guaranteed to have only one unique value in the BST that is closest to the target.
     * @param root
     * @param target
     * @return
     */
    // https://www.cnblogs.com/grandyang/p/5237170.html
    // 利用BST的性质 time:O(logN) space:O(1)
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            // 差值更小的更新
            if (Math.abs(res - target) >= root.val - target) {
                // 更新res
                res = root.val;
            }
            root = target < root.val ? root.left : root.right;
        }
        return res;
    }

    // 递归的形式
    public int closestValue2(TreeNode root, double target) {
        return helper(root, target, root.val);
    }

    public int helper(TreeNode root, double target, int val) {
        if (root == null) return val; //这里用的很妙
        if (Math.abs(root.val - target) < Math.abs(val - target)) {
            val = root.val;
        }
        if (root.val < target) {
            val = helper(root.right, target, val);
        } else if (root.val > target) {
            val = helper(root.left, target, val);
        }
        return val;
    }
}
