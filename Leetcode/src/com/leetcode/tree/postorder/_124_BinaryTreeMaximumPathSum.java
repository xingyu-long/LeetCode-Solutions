/*
 * @Date: 2019-11-13 19:40:31
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 14:52:56
 */
package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _124_BinaryTreeMaximumPathSum {

    static int res; // 这样有利于保存
    // time:O(n) space:O(h) call stack
    // http://zxi.mytechroad.com/blog/tree/leetcode-124-binary-tree-maximum-path-sum/
    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }

    public static int helper(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        int sum = left + right + root.val; // 这个作为结果就是求左边最大的情况+当前节点+右边最大情况
        res = Math.max(res, sum);
        return Math.max(left, right) + root.val;
    }
}
