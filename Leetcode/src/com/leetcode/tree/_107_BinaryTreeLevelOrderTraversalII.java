package com.leetcode.tree;

import com.leetcode.lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _107_BinaryTreeLevelOrderTraversalII {

    /**
     * 107. Binary Tree Level Order Traversal II
     * When: 2019/04/22
     *
     * solution:
     * 利用queue 进行广度遍历 并且使用ArrayList的特性.add(0,x)这样可以逆序
     *
     * Time: O(n)
     * Space: O(n)
     * @param root
     * @return
     */
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

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int a = 1;
        int b = 2;
        list.add(0, a);
        list.add(0, b); // 每次插入0这个位置，会把其他放在后面
        System.out.println(list.toString());
    }
}