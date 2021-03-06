/*
 * @Date: 03/09/2021 10:04:32
 * @LastEditTime: 03/09/2021 10:05:54
 * @Description: BFS/DFS
 */
package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.leetcode.common.TreeNode;

public class _623_AddOneRowToTree {
    // 一开始没有想到，之前差一点就想到了。
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            if (--d == 0)
                return root;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.poll();
                if (d == 1) {
                    TreeNode left = curr.left;
                    TreeNode right = curr.right;
                    curr.left = new TreeNode(v);
                    curr.right = new TreeNode(v);
                    curr.left.left = left;
                    curr.right.right = right;
                } else {
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                }
            }
        }
        return root;
    }
}
