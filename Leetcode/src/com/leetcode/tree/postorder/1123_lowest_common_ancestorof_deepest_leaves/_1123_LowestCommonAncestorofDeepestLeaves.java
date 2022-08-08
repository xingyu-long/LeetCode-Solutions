package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

/**
 * @Date: 05/20/2020, 08/23/2020
 * @Description: Tree, Postorder
 **/
public class _1123_LowestCommonAncestorofDeepestLeaves {

    static class Data {
        TreeNode node;
        int depth;

        public Data(TreeNode n, int d) {
            node = n;
            depth = d;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // The lowest common ancestor of a set S of nodes is the node A with the 
        // largest depth such that every node in S is in the subtree with root A.
        // 找最深的那个。深度作为判断咯
        Data res = dfs(root);
        return res.node;
    }

    public Data dfs(TreeNode root) {
        if (root == null) {
            return new Data(null, 0);
        }
        Data left = dfs(root.left);
        Data right = dfs(root.right);
        if (left.depth > right.depth) {
            return new Data(left.node, left.depth + 1);
        } else if (right.depth > left.depth) {
            return new Data(right.node, right.depth + 1);
        } else {
            return new Data(root, left.depth + 1);
        }
    }
}