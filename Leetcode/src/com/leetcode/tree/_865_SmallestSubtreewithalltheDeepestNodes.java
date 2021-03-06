package com.leetcode.tree;

import com.leetcode.common.TreeNode;

/*
 * @Date: 02/06/2020 23:51:46
 * @LastEditTime: 12/12/2020 11:11:38
 * @Description: Postorder
 */

public class _865_SmallestSubtreewithalltheDeepestNodes {

    public class Data {
        TreeNode node;
        int depth;

        public Data(TreeNode r, int d) {
            node = r;
            depth = d;
        }
    }

    // one pass time:O(n) space:O(n)
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    public Data dfs(TreeNode root) {
        if (root == null)
            return new Data(null, 0);
        Data left = dfs(root.left);
        Data right = dfs(root.right);

        if (left.depth > right.depth)
            return new Data(left.node, left.depth + 1);
        if (left.depth < right.depth)
            return new Data(right.node, right.depth + 1);
        // 相同的话，就把root传回去
        return new Data(root, left.depth + 1);
    }

    class Node {
        TreeNode root;
        int depth;

        // 之前是上面的Node里面初始化没有处理好
        public Node(TreeNode r, int d) {
            root = r;
            depth = d;
        }
    }

    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
        Node res = dfs2(root);
        return res.root;
    }

    public Node dfs2(TreeNode root) {
        if (root == null) {
            return new Node(root, 0);
        }

        Node left = dfs2(root.left);
        Node right = dfs2(root.right);
        if (left.depth == right.depth) {
            return new Node(root, left.depth + 1);
        } else if (left.depth > right.depth) {
            return new Node(left.root, left.depth + 1);
        } else {
            return new Node(right.root, right.depth + 1);
        }
    }
}
