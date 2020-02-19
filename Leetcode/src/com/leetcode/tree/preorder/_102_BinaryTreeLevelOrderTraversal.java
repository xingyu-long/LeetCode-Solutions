package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102_BinaryTreeLevelOrderTraversal {

    /**
     * 102. Binary Tree Level Order Traversal
     * When: 2019/04/12
     * Review1:2019/7/25
     * review2:2019/9/11
     * <p>
     * solution:
     * 同样使用先序遍历 只是需要考虑到depth
     *
     * @param root
     * @return
     */
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
        //利用bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 如何标记在哪一层？利用每一层保留的size 然后循环就可以作为标记
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
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
