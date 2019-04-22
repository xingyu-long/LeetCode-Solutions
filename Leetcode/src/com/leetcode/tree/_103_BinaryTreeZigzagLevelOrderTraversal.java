package com.leetcode.tree;

import com.leetcode.lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _103_BinaryTreeZigzagLevelOrderTraversal {

    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     * When: 2019/04/22
     *
     * solution: 利用非递归形式 进行BFS 然后中途用个变量来改编插入方向
     *
     * time: O(n)
     * space:O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean order = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (order) {
                    cur.add(node.val);
                } else {
                    cur.add(0, node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(cur);
            if (order) {
                order = false;
            } else {
                order = true;
            }
        }
        return res;
    }
}
