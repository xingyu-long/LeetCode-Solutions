package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _513_FindBottomLeftTreeValue {

    // DFS
    public int res;
    public int maxDepth; // max depth of curr level, and it will be updated once.
    // 类似于那个按照层来输出的那个题，每次新加一层的条件就是通过每一层的第一个决定的。
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;
        res = root.val;
        maxDepth = 0;
        dfs(root, 1);
        return res;
    }
    
    public void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth > maxDepth) {
            res = root.val;
            maxDepth = depth;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    // BFS
    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (i == 0) {
                    res = curr.val;
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return res;
    }
}