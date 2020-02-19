package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _1343_MaximumProductofSplittedBinaryTree {
    // 1343. Maximum Product of Splitted Binary Tree

    // time:O(n) space:O(n)
    long res = Integer.MIN_VALUE;
    long sum = 0;
    int mod = (int) Math.pow(10, 9) + 7;
    public int maxProduct(TreeNode root) {
        if (root == null) return 0;
        sum = Sum(root);
        // 为啥不是在乘积后mod， 因为这样会丢失精度吧。
        res %= mod;
        return (int) res;
    }

    public long Sum(TreeNode root) {
        if (root == null) return 0;
        long left = Sum(root.left);
        long right = Sum(root.right);
        long total = left + right + root.val;
        // 从这里每次断开root 和上面的联系
        res = Math.max(res, total * (sum - total));
        return total;
    }
}
