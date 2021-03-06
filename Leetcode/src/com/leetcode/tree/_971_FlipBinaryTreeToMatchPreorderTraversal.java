/*
 * @Date: 03/29/2021 09:04:23
 * @LastEditTime: 03/29/2021 09:04:44
 * @Description: Preorder
 */
package com.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.leetcode.common.TreeNode;

public class _971_FlipBinaryTreeToMatchPreorderTraversal {
    private int i;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        i = 0;
        List<Integer> res = new ArrayList<>();
        return dfs(root, voyage, res) ? res : Arrays.asList(-1);
    }

    private boolean dfs(TreeNode root, int[] v, List<Integer> res) {
        if (root == null)
            return true;
        if (root.val != v[i++])
            return false;
        if (root.left != null && root.left.val != v[i]) {
            res.add(root.val);
            return dfs(root.right, v, res) && dfs(root.left, v, res);
        }
        return dfs(root.left, v, res) && dfs(root.right, v, res);
    }
}
