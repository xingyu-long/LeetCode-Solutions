/*
 * @Date: 2019-09-20 16:15:21
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 15:55:02
 */
package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _107_BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>(); // add和offer的区别呢？
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                cur.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(0, cur);
        }
        return res;
    }

    // DFS, time:O(n) space:O(n)
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        // 利用postorder
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, res, 0);
        return res;
    }

    public void helper(TreeNode root, List<List<Integer>> res, int depth) {
        if (root == null) return;

        // here is my implement for adding list into res
        if (res.size() == depth) {
            // 需要这里注意添加到前面的数组
            res.add(0, new ArrayList<>());
        }
        res.get(res.size() - depth - 1).add(root.val);
        helper(root.left, res, depth + 1);
        helper(root.right, res, depth + 1);
    }
}
