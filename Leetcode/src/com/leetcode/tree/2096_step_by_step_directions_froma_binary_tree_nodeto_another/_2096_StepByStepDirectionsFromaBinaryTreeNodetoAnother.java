/*
 * @Date: 07/17/2022 16:37:00
 * @LastEditTime: 07/17/2022 16:38:01
 * @Description: You need to fill out
 */
package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.common.TreeNode;

public class _2096_StepByStepDirectionsFromaBinaryTreeNodetoAnother {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        // 1) Find lowest common ancestor
        TreeNode lca = getLCA(root, startValue, destValue);
        List<String> lcaToStart = new ArrayList<>();
        List<String> lcaToDest = new ArrayList<>();
        // 2) build path for start and dest value with LCA
        findPath(lca, lcaToStart, startValue);
        findPath(lca, lcaToDest, destValue);

        // 3) reverse to U
        for (int i = 0; i < lcaToStart.size(); i++) {
            lcaToStart.set(i, "U");
        }

        return String.join("", lcaToStart) + String.join("", lcaToDest);
    }

    public TreeNode getLCA(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = getLCA(root.left, p, q);
        TreeNode right = getLCA(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    public boolean findPath(TreeNode root, List<String> path, int val) {
        if (root == null)
            return false;
        if (root.val == val) {
            return true;
        }
        path.add("L");
        if (findPath(root.left, path, val))
            return true;
        path.remove(path.size() - 1);

        path.add("R");
        if (findPath(root.right, path, val))
            return true;
        path.remove(path.size() - 1);

        return false;
    }
}
