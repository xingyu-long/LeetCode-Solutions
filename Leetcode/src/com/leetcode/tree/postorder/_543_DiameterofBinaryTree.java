/*
 * @Date: 2019-09-15 16:08:16
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 14:57:52
 */
package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _543_DiameterofBinaryTree {

    private static int res = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return res;
    }

    // time:O(n) space:O(h)
    // https://www.youtube.com/watch?v=VuezJmuIyU4
    public static int helper(TreeNode root) {
        if (root == null) return 0; // 这里设置为0，则只要有一个点就是+1这种存在。但是这个题求的是edge 就是两个点的话 结果只有1
        int left = helper(root.left);
        int right = helper(root.right);
        // 这个在 +1 操作之前，所以就符合要求
        res = Math.max(res, left + right);// 这里需要注意计数的问题
        return Math.max(left, right) + 1;
    }
}
