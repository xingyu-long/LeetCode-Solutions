package com.leetcode.tree;

import com.leetcode.common.TreeNode;

/**
 * @Date: 05/21/2020
 * @Description: Tree, postOrder
 **/
public class _1026_MaximumDifferenceBetweenNodeandAncestor {

    // 找每个sub tree的最小最大值
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    // 相当于前序一直传min,max然后后序传回来！
    public int dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        int left = dfs(root.left, min, max);
        int right = dfs(root.right, min, max);
        return Math.max(max - min, Math.max(left, right));
    }
}
