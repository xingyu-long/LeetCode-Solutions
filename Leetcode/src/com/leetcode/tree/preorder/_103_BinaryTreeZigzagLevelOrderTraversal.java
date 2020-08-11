/*
 * @Date: 2019-09-20 16:57:04
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 15:55:15
 */
package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _103_BinaryTreeZigzagLevelOrderTraversal {
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

    // 利用dfs。无外乎就是三种order只是每次处理会有其他的要求，根据要求再改。tree都逃离不了三种基本order
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, 0, root);
        return res;
    }

    public void helper(List<List<Integer>> res, int depth, TreeNode root) {
        if (root == null) return;
        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }
        if (depth % 2 != 0) res.get(depth).add(0, root.val);
        else res.get(depth).add(root.val);
        helper(res, depth + 1, root.left);
        helper(res, depth + 1, root.right);
    }
}
