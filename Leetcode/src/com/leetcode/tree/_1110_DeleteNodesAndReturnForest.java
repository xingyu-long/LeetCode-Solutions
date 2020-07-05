package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Date: 04/20/2020
 * @Description: Tree
 **/
public class _1110_DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> res = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int num : to_delete) {
            set.add(num);
        }
        dfs(res, root, set, true);
        return res;
    }

    // https://www.youtube.com/watch?v=ZmNVk0irtRk&feature=youtu.be
    // 什么时候加入结果集？ 当前的点是顶点，即一开始的顶点或者是父节点被删除之后剩下的节点
    // 置空的两种方法：（1）parent check on child's value via if statement
    public void dfs(List<TreeNode> res, TreeNode root, HashSet<Integer> set, boolean isRoot) {
        if (root == null) {
            return;
        }
        boolean deleted = set.contains(root.val);
        if (!deleted && isRoot) {
            res.add(root);
        }
        dfs(res, root.left, set, deleted);
        if (root.left != null && set.contains(root.left.val)) {
            root.left = null;
        }
        dfs(res, root.right, set, deleted);
        if (root.right != null && set.contains(root.right.val)) {
            root.right = null;
        }
    }

    // (2) parent ask for child's response via return value;
    public TreeNode dfs2(List<TreeNode> res, TreeNode root, HashSet<Integer> set, boolean isRoot) {
        if (root == null) {
            return null;
        }
        boolean deleted = set.contains(root.val);
        if (!deleted && isRoot) {
            res.add(root);
        }
        root.left = dfs2(res, root.left, set, deleted);
        root.right = dfs2(res, root.right, set, deleted);
        return deleted ? null : root;
    }

    // parent ask for child's response via return value;
    public boolean helper(List<TreeNode> res, TreeNode root, HashSet<Integer> set,
        boolean parentDeleted) {
        if (root == null) {
            return true;
        }
        boolean deleted = set.contains(root.val);
        if (parentDeleted && !deleted) {
            res.add(root);
        }
        if (helper(res, root.left, set, deleted)) {
            root.left = null;
        }
        if (helper(res, root.right, set, deleted)) {
            root.right = null;
        }
        return deleted;
    }
}
