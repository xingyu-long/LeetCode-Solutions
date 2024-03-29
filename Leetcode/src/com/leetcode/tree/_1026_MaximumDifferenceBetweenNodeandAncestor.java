/*
 * @Date: 08/11/2020 16:07:14
 * @LastEditTime: 06/11/2022 19:06:54
 * @Description: Tree, Preorder, Postorder
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;


public class _1026_MaximumDifferenceBetweenNodeandAncestor {

    // 找每个sub tree的最小最大值
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }
    
    public int dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return max - min;
        }
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);
        int left = dfs(root.left, min, max);
        int right = dfs(root.right, min, max);
        return Math.max(left, right);
    }



    // 自己实现的方法，但其实差不多。
    int res;

    public int maxAncestorDiff2(TreeNode root) {
        if (root == null) return 0;
        res = 0;
        dfs2(root, root.val, root.val);
        return res;
    }

    public void dfs2(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }

        res = Math.max(res, Math.max(Math.abs(min - root.val), Math.abs(max - root.val)));
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        dfs2(root.left, min, max);
        dfs2(root.right, min, max);
    }
}
