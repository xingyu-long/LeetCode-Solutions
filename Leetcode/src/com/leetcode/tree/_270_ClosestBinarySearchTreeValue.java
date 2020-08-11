package com.leetcode.tree;

import com.leetcode.common.TreeNode;

/**
 * @Date: 05/13/2020
 * @Description: Tree,
 **/
public class _270_ClosestBinarySearchTreeValue {

    /**
     * 270. Closest Binary Search Tree Value
     * <p>
     * Given a non-empty binary search tree and a target value, find the value in the BST that is
     * closest to the target.
     * <p>
     * Note:
     * <p>
     * Given target value is a floating point. You are guaranteed to have only one unique value in
     * the BST that is closest to the target.
     *
     * @param root
     * @param target
     * @return
     */
    // https://www.cnblogs.com/grandyang/p/5237170.html
    // 利用BST的性质 time:O(logN) space:O(1)
    public int closestValue(TreeNode root, double target) {
        double diff = Double.MAX_VALUE;
        int res = -1;
        TreeNode curr = root;
        while (curr != null) {
            if (Math.abs(target - curr.val) < diff) {
                diff = Math.abs(target - curr.val);
                res = curr.val;
            }
            curr = curr.val > target ? curr.left : curr.right;
        }
        return res;
    }

    // 递归的形式
    public int closestValue2(TreeNode root, double target) {
        return helper(root, target, root.val);
    }

    public int helper(TreeNode root, double target, int closest) {
        if (root == null) {
            return closest; //这里用的很妙
        }
        if (Math.abs(root.val - target) < Math.abs(closest - target)) {
            closest = root.val;
        }
        if (root.val < target) {
            closest = helper(root.right, target, closest);
        } else if (root.val > target) {
            closest = helper(root.left, target, closest);
        }
        return closest;
    }
}
