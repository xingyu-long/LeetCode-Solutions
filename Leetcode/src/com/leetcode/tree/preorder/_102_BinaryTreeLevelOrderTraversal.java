/*
 * @Date: 2019-09-11 16:46:33
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-21 14:27:56
 */
package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102_BinaryTreeLevelOrderTraversal {
    // 同样使用先序遍历 只是需要考虑到depth
    // https://www.youtube.com/watch?v=gcR28Hc2TNQ
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(res, root, 0);
        return res;
    }

    // DFS
    public void dfs(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        if (res.size() == level) { // 利用那个当前的个数与层数的关系
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }

    // BFS
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            // 逐层扫描
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll(); // 加入现在这个节点的值
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
