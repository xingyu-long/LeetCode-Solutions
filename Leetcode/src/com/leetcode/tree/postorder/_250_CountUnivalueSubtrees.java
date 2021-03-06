/*
 * @Date: 2019-09-15 17:05:50
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 15:19:48
 */
package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _250_CountUnivalueSubtrees {
    
    private int res;
    // time:O(n) space:O(n)
    public int countUnivalSubtrees(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }

    // 需要从一开始的brute force开始思考，然后再思考优化的方向，例如在back的过程顺便计数。
    public boolean helper(TreeNode root) {
        if (root == null) return true;
        boolean left = helper(root.left);
        boolean right = helper(root.right);

        if (left && right) {
            //验证左右子树不为null的情况是否与root相同
            if (root.left != null && root.val != root.left.val) {
                return false;
            }
            if (root.right != null && root.val != root.right.val) {
                return false;
            }
            res++;
        }
        return left && right;
    }
}