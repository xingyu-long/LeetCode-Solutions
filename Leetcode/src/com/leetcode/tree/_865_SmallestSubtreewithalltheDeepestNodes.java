package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.HashMap;

public class _865_SmallestSubtreewithalltheDeepestNodes {

    // 这里跟那个LCA有异曲同工之妙。
    // 先找出最大的深度，然后看在那个深度有什么点返回，这里就和LCA差不多了！
    HashMap<TreeNode, Integer> map;
    int max;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) return null;
        map = new HashMap<>();
        map.put(null, -1);
        dfs(root, null);
        for (int d : map.values()) {
            max = Math.max(max, d);
        }
        return findMaxDepthNode(root);
    }

    public void dfs(TreeNode root, TreeNode parent) {
        if (root == null) return;
        map.put(root, map.get(parent) + 1);
        dfs(root.left, root);
        dfs(root.right, root);
    }

    public TreeNode findMaxDepthNode(TreeNode root) {
        if (root == null) return null;
        if (map.get(root) == max) return root;
        TreeNode left = findMaxDepthNode(root.left);
        TreeNode right = findMaxDepthNode(root.right);
        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }

    public class Data {
        TreeNode node;
        int depth;

        public Data(TreeNode r, int d) {
            node = r;
            depth = d;
        }
    }

    // one pass time:O(n) space:O(n)
    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
        return dfs(root).node;
    }

    public Data dfs(TreeNode root) {
        if (root == null) return new Data(null, 0);
        Data left = dfs(root.left);
        Data right = dfs(root.right);

        if (left.depth > right.depth) return new Data(left.node, left.depth + 1);
        if (left.depth < right.depth) return new Data(right.node, right.depth + 1);
        // 相同的话，就把root传回去
        return new Data(root, left.depth + 1);
    }
}
