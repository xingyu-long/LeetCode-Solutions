/*
 * @Date: 08/17/2021 10:42:06
 * @LastEditTime: 08/17/2021 10:43:16
 * @Description: Preorder with current maximum
 */
package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

public class _1448_CountGoodNodesInBinaryTree {
    // time: O(Nodes), space: O(height)
    public int goodNodes(TreeNode root) {
        // preorder
        if (root == null) return 0;
        return dfs(root, Integer.MIN_VALUE);
    }
    
    private int dfs(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val >= max) {
            res++;
            max = root.val;
        }
        res += dfs(root.left, max);
        res += dfs(root.right, max);
        return res;
    }
}

